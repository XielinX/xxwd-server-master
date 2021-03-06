package com.xlx.xxwd.controller;

import com.alibaba.fastjson.JSON;
import com.xlx.xxwd.adapter.WeChatAdapter;
import com.xlx.xxwd.dto.LoginDTO;
import com.xlx.xxwd.dto.ResultDTO;
import com.xlx.xxwd.dto.SeesionDTO;
import com.xlx.xxwd.dto.TokenDTO;
import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.exception.ErrorCodeException;
import com.xlx.xxwd.entity.User;
import com.xlx.xxwd.service.UserService;
import com.xlx.xxwd.util.DigestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Controller层:login
 *
 * @author xielx on 2019/7/2
 */
@RestController
@Slf4j
public class LoginController {

  @Resource
  private WeChatAdapter weChatAdapter;
  @Resource
  private UserService userService;

  /**
   * 思路:
   * 将微信端返回的数据:用户信息,凭证,获取session_key的code
   * 进行校验:signature = SHA1(rawData + session_key)
   * @param loginDTO 微信端数据:用户信息,凭证,获取session_key的code
   * @return dto
   */
  @RequestMapping(value = "/api/login")
  public ResultDTO login(@RequestBody LoginDTO loginDTO) {
    log.info("微信端返回数据:[{}]", loginDTO);
    SeesionDTO seesionDTO;
    try {
      //调用微信API接口(jscode2session)获取登录凭证,校验成功得到的openid和session_key
       seesionDTO = weChatAdapter.jscode2session(loginDTO.getCode());
      //检验微信端传过来的用户信息是否合法
      DigestUtil.checkDigest(loginDTO.getRawData(), seesionDTO.getSessionKey(), loginDTO.getSignature());
    } catch (ErrorCodeException e) {
      log.error("登录凭证校验异常: {}", e.getMessage());
      return ResultDTO.failed(e);
    } catch (Exception e) {
      log.error("其他异常: {}", e.getMessage());
      return ResultDTO.failed(ErrorCodeEnum.UNKNOWN_ERROR);
    }
  
    //token,用于自定义登录态
    String token = UUID.randomUUID().toString();
    // 获取微信端的微信用户信息
    User user = JSON.parseObject(loginDTO.getRawData(), User.class);
    user.setToken(token);
    user.setOpenid(seesionDTO.getOpenid());
    user.setStatus(Boolean.TRUE);
    userService.insertOrUpdate(user);
  
    TokenDTO tokenDTO = new TokenDTO();
    tokenDTO.setToken(token);
  
    return ResultDTO.success(tokenDTO);
  }

}

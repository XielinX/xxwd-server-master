package com.xlx.xxwd.controller;

import com.alibaba.fastjson.JSON;
import com.xlx.xxwd.adapter.WeChatAdapter;
import com.xlx.xxwd.dto.LoginDTO;
import com.xlx.xxwd.dto.ResultDTO;
import com.xlx.xxwd.dto.SeesionDTO;
import com.xlx.xxwd.dto.TokenDTO;
import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.exception.ErrorCodeException;
import com.xlx.xxwd.model.User;
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
    log.info("login request:[{}]", loginDTO);
    try {

      //使用code调用微信API获取登录凭证,校验成功后的openid和session_key
      SeesionDTO seesionDTO = weChatAdapter.jscode2session(loginDTO.getCode());

      log.info("login get session:[{}]", seesionDTO);
      //检验微信端传过来的用户信息是否合法
      DigestUtil.checkDigest(loginDTO.getRawData(), seesionDTO.getSessionKey(), loginDTO.getSignature());

      //token,用于自定义登录态
      String token = UUID.randomUUID().toString();
      // 获取微信端的微信用户信息
      User user = JSON.parseObject(loginDTO.getRawData(), User.class);
      user.setToken(token);
      user.setOpenid(seesionDTO.getOpenid());

      userService.insertOrUpdate(user);

      TokenDTO tokenDTO = new TokenDTO();
      tokenDTO.setToken(token);

      return ResultDTO.success(tokenDTO);

    } catch (ErrorCodeException e) {
      log.error("login error,info: {}", loginDTO, e);
      return ResultDTO.failed(e);
    } catch (Exception e) {
      log.error("login error,info: {}", loginDTO, e);
      return ResultDTO.failed(ErrorCodeEnum.UNKNOWN_ERROR);
    }
  }
}

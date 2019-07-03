package com.xlx.xxwd.adapter;

import com.alibaba.fastjson.JSON;
import com.xlx.xxwd.dto.SeesionDTO;
import com.xlx.xxwd.exception.ErrorCodeEnum;
import com.xlx.xxwd.exception.ErrorCodeException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 登录凭证校验
 *
 * @author xielx on 2019/7/2
 */
@Service
public class WeChatAdapter {

  private static final Logger logger = LoggerFactory.getLogger(WeChatAdapter.class);
  @Value("${wechat.appid}")
  private String appid;

  @Value("${wechat.secret}")
  private String secret;


  /**
   * 登录凭证校验
   * 获取用户唯一标识 OpenID 和 会话密钥 session_key。
   * @param code  通过wx.login()获取的临时登录凭证code
   * @return .
   */
  public SeesionDTO jscode2session(String code){
    // %s格式化字符
    String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

    OkHttpClient okHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
            .addHeader("content-type","application/json")
            .url(String.format(url,appid,secret,code))
            .build();

    try {
      Response response = okHttpClient.newCall(request).execute();
      if (response.isSuccessful()){
        //响应数据
        String result = response.body().toString();
        SeesionDTO seesionDTO = JSON.parseObject(result,SeesionDTO.class);
        logger.info("jscode2session get url -> {},get info -> {}",String.format(url,appid,secret,code),JSON.toJSONString(seesionDTO));
        return seesionDTO;
      }else {
        logger.info("jscode2session code []",code);
        throw  new ErrorCodeException(ErrorCodeEnum.OBTAIN_OPEN_ID_FAILED);
      }
    } catch (IOException e) {
      logger.error("jscode2session authorized error: {}",code,e);
      throw  new ErrorCodeException(ErrorCodeEnum.OBTAIN_OPEN_ID_FAILED);
    }
  }

}

package com.xlx.xxwd.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 调用jscode2session接口的返回值
 * Object:返回的json数据包
 *
 * @author xielx on 2019/7/2
 */
@Data
public class SeesionDTO {

  //用户唯一标识
  private String openid;

  //会话密钥
  @JSONField(name = "session_key")
  private String sessionKey;

}

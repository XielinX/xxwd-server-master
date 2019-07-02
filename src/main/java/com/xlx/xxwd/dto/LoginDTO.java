package com.xlx.xxwd.dto;

import lombok.Data;

/**
 * login
 *
 * @author xielx on 2019/7/2
 */
@Data
public class LoginDTO {

  // 微信用户信息的原始数据
  private String rawData;

  // 验证用户信息是否被篡改过
  private String signature;

  // 用户获取session_key的code
  private String code;
}

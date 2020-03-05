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
  
  /**
   *  用户唯一标识
   */
  private String openid;
  
  /**
   * 会话密钥
   */
  @JSONField(name = "session_key")
  private String sessionKey;
  /**
   * 用户在开放平台的唯一标识符,有条件返回
   */
  //private String unionid;
  /**
   * 错误码
   * -1: 系统忙;
   * 0: 成功;
   * 40029: code无效
   * 45011: 频率限制
   */
  //private Integer errorcode;
  /**
   * 错误信息
   */
 // private String errmsg;
  
  

}

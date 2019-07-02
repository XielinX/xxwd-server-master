package com.xlx.xxwd.dto;

import lombok.Data;

/**
 * user
 *
 * @author xielx on 2019/7/2
 */
@Data
public class UserDTO {

  // 微信名称
  private String nickName;

  //性别
  private Integer gender;

  //语言
  private String language;

  // 头像
  private String avatarUrl;

  // 国家
  private String country;

  // 省份
  private String provinces;

  // 城市
  private String city;
}


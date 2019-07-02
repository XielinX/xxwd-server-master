package com.xlx.xxwd.exception;

/**
 * @author: xielx on 2019/7/2
 */
public enum ErrorCodeEnum implements IErrorCode {

  NETWORK_ERROR(1001, "网络错误请重试"),
  INVIDATE_PARAM(1002, "无效的参数"),
  OBTAIN_OPEN_ID_FAILED(1003, "登录失败,请重新尝试"),
  UNKNOWN_ERROR(1004, "未知错误"),
  USER_NOT_FOUND(1005, "登录异常,请重新登录"),
  SIGNATURE_ERROR(1006, "验证失败,传递信息有误");


  private Integer code;
  private String message;

  ErrorCodeEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }


  @Override
  public Integer getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}

package com.xlx.xxwd.exception;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 自定义异常
 *
 * @author xielx on 2019/7/2
 */
public class ErrorCodeException extends RuntimeException implements IErrorCode {

  @Setter
  @Getter
  private Integer code;


  @Getter
  @Setter
  private String message;


  public ErrorCodeException(IErrorCode iErrorCode){
    this.code = iErrorCode.getCode();
    this.message = iErrorCode.getMessage();
  }


  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}

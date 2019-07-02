package com.xlx.xxwd.dto;

import com.xlx.xxwd.exception.IErrorCode;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 结果
 *
 * @author xielx on 2019/7/2
 */
public class ResultDTO {

  private Integer code;
  private String message;
  private Object data;


  public ResultDTO(Integer code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }

  /**
   * 执行成功
   * @param object 返回参数
   * @return dto
   */
  public static ResultDTO success(Object object){
    return new ResultDTO(200,"请求成功",object);
  }


  /**
   * 执行失败
   * @param iErrorCode 异常接口
   * @return dto
   */
  public static  ResultDTO failed(IErrorCode iErrorCode){
    return new ResultDTO(iErrorCode.getCode(),iErrorCode.getMessage(),null);
  }
}

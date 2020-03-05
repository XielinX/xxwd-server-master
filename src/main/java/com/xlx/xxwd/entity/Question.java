package com.xlx.xxwd.entity;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

@Data
public class Question implements Serializable {
    private Integer id;

    private Integer userId;

    private String title;
    
    
    private Integer commentCount;
    
    private Integer viewCount;
    
    private Integer likeCount;
    
    private Long gmtCreate;
    
    private Long gmtModified;
    
    private Byte status;
    
    private String content;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }


}
package com.xlx.xxwd.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author xielx on 2019/7/2
 */
public class User implements Serializable {
    
    /**
     * 主键
     */
    private Integer id;
    
    /**
     * openid
     */
    private String openid;
    /**
     * token
     */
    private String token;
    
    /**
     * 昵称
     */
    private String nickName;
    
    /**
     * 头像url
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private Byte gender;
    
    /**
     * 创建时间
     */
    private Long gmtCreate;
    
    /**
     * 修改时间
     */
    private Long gmtModified;
    
    /**
     *  状态
     */
    private Boolean status;
    
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
    
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getOpenid() {
        return openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }
    
    public String getAvatarUrl() {
        return avatarUrl;
    }
    
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl == null ? null : avatarUrl.trim();
    }
    
    public Byte getGender() {
        return gender;
    }
    
    public void setGender(Byte gender) {
        this.gender = gender;
    }
    
    public Long getGmtCreate() {
        return gmtCreate;
    }
    
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    
    public Long getGmtModified() {
        return gmtModified;
    }
    
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
    
    public Boolean getStatus() {
        return status;
    }
    
    public void setStatus(Boolean status) {
        this.status = status;
    }
}
package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 14:57
 * @Description:
 */
public class UserCollection {
     private Integer id;
     private Integer user_id;
     private Integer business_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }
}

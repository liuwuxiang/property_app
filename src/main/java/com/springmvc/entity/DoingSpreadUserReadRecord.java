package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/11/7 00:54
 * @Description:推广消息用户已读记录实体类
 */
public class DoingSpreadUserReadRecord {
    private Integer id;
    private Integer message_id;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}

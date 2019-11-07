package com.springmvc.entity;

import java.util.Date;

public class OpenMemberCardWxPayOrder {
    private Integer id;
    private String order_no;
    private Integer user_id;
    private Integer state;
    private Date create_time;
    private Integer card_type_id;
    private Integer user_type;

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getCard_type_id() {
        return card_type_id;
    }

    public void setCard_type_id(Integer card_type_id) {
        this.card_type_id = card_type_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}

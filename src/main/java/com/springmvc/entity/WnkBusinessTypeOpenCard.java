package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/11/5 00:46
 * @Description:万能卡商家分类开卡记录(用于记录万能卡分类需要用户开卡的用户开卡记录)实体类
 */
public class WnkBusinessTypeOpenCard {
    private Integer id;
    private Integer user_id;
    private Integer business_type_id;
    private Date open_card_time;
    private Date card_end_time;
    private Integer business_id;
    private String qrcode;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

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

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }

    public Date getOpen_card_time() {
        return open_card_time;
    }

    public void setOpen_card_time(Date open_card_time) {
        this.open_card_time = open_card_time;
    }

    public Date getCard_end_time() {
        return card_end_time;
    }

    public void setCard_end_time(Date card_end_time) {
        this.card_end_time = card_end_time;
    }
}

package com.springmvc.entity;

import java.util.Date;

public class WnkRealNameAuthentication {
    private Integer id;
    private Integer user_id;
    private Integer type;
    private String real_name;
    private String id_card_number;
    private String school;
    private String mobile;
    private Date authentication_date;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(String id_card_number) {
        this.id_card_number = id_card_number;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getAuthentication_date() {
        return authentication_date;
    }

    public void setAuthentication_date(Date authentication_date) {
        this.authentication_date = authentication_date;
    }
}

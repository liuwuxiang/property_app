package com.springmvc.entity;

import java.util.Date;

public class Codes {
    private Integer id;
    private String send_number;
    private Integer send_type;
    private String code;
    private Date send_time;
    private Integer make_type;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSend_number() {
        return send_number;
    }

    public void setSend_number(String send_number) {
        this.send_number = send_number;
    }

    public Integer getSend_type() {
        return send_type;
    }

    public void setSend_type(Integer send_type) {
        this.send_type = send_type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public Integer getMake_type() {
        return make_type;
    }

    public void setMake_type(Integer make_type) {
        this.make_type = make_type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

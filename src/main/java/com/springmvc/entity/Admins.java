package com.springmvc.entity;

import java.util.Date;

public class Admins {
    private Integer id;
    private String name;
    private String mobile;
    private String position;
    private String login_pwd;
    private Integer admin_type_id;
    private Date join_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public Integer getAdmin_type_id() {
        return admin_type_id;
    }

    public void setAdmin_type_id(Integer admin_type_id) {
        this.admin_type_id = admin_type_id;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }
}

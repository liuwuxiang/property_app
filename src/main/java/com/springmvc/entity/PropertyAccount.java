package com.springmvc.entity;

import java.util.Date;

public class PropertyAccount {
    private Integer id;
    private Integer residentials_id;
    private Integer primary_account_state;
    private String account;
    private String login_pwd;
    private String contact_name;
    private String contact_mobile;
    private Date create_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResidentials_id() {
        return residentials_id;
    }

    public void setResidentials_id(Integer residentials_id) {
        this.residentials_id = residentials_id;
    }

    public Integer getPrimary_account_state() {
        return primary_account_state;
    }

    public void setPrimary_account_state(Integer primary_account_state) {
        this.primary_account_state = primary_account_state;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_mobile() {
        return contact_mobile;
    }

    public void setContact_mobile(String contact_mobile) {
        this.contact_mobile = contact_mobile;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}

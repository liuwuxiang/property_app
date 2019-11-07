package com.springmvc.entity;

import java.util.Date;

public class WnkBusinessAccount {
    private Integer id;
    private String  mobile;
    private String  login_pwd;
    private String  pay_pwd;
    private Date    join_time;
    private Integer state;
    private Double  balance;
    private Integer consumption_integral;
    private Double level_integral;
    private Integer rose_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getPay_pwd() {
        return pay_pwd;
    }

    public void setPay_pwd(String pay_pwd) {
        this.pay_pwd = pay_pwd;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getConsumption_integral() {
        return consumption_integral;
    }

    public void setConsumption_integral(Integer consumption_integral) {
        this.consumption_integral = consumption_integral;
    }

    public Double getLevel_integral() {
        return level_integral;
    }

    public void setLevel_integral(Double level_integral) {
        this.level_integral = level_integral;
    }

    public Integer getRose_number() {
        return rose_number;
    }

    public void setRose_number(Integer rose_number) {
        this.rose_number = rose_number;
    }
}

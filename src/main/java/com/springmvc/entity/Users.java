package com.springmvc.entity;

import java.util.Date;

public class Users {

    private Integer id;
    private String mobile;
    private String login_pwd;
    private String wx_unionid;
    private String nick_name;
    private String header;
    private Integer sex;
    private Integer recommend_user_id;
    private Integer member_level_id;
    private String email;
    private String user_qrcode_url;
    private String pay_pwd;
    private Integer is_microfinance;
    private Double consumption_integral;
    private Double general_integral;
    private Date register_time;
    private Integer member_star;
    private Integer silver_coin_balance;
    private Integer credit_number;
    private Date credit_date;
    private Integer member_card_level;
    private String wnk_qrcode;
    private Double user_integral;
    private Integer recommend_type;
    private String getui_appid;

    public String getGetui_appid() {
        return getui_appid;
    }

    public void setGetui_appid(String getui_appid) {
        this.getui_appid = getui_appid;
    }

    public Integer getRecommend_type() {
        return recommend_type;
    }

    public void setRecommend_type(Integer recommend_type) {
        this.recommend_type = recommend_type;
    }

    public Double getUser_integral() {
        return user_integral;
    }

    public void setUser_integral(Double user_integral) {
        this.user_integral = user_integral;
    }

    public String getWnk_qrcode() {
        return wnk_qrcode;
    }

    public void setWnk_qrcode(String wnk_qrcode) {
        this.wnk_qrcode = wnk_qrcode;
    }

    public Integer getMember_card_level() {
        return member_card_level;
    }

    public void setMember_card_level(Integer member_card_level) {
        this.member_card_level = member_card_level;
    }

    public Date getCredit_date() {
        return credit_date;
    }

    public void setCredit_date(Date credit_date) {
        this.credit_date = credit_date;
    }

    public Integer getCredit_number() {
        return credit_number;
    }

    public void setCredit_number(Integer credit_number) {
        this.credit_number = credit_number;
    }

    public Integer getSilver_coin_balance() {
        return silver_coin_balance;
    }

    public void setSilver_coin_balance(Integer silver_coin_balance) {
        this.silver_coin_balance = silver_coin_balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        if (mobile == null){
            return "";
        }
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLogin_pwd() {
        if (login_pwd == null){
            return "123456";
        }
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getWx_unionid() {
        if (wx_unionid == null){
            return "";
        }
        return wx_unionid;
    }

    public void setWx_unionid(String wx_unionid) {
        this.wx_unionid = wx_unionid;
    }

    public String getNick_name() {
        if (nick_name == null){
            return "";
        }
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHeader() {
        if (header == null){
            return "";
        }
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getRecommend_user_id() {
        return recommend_user_id;
    }

    public void setRecommend_user_id(Integer recommend_user_id) {
        this.recommend_user_id = recommend_user_id;
    }

    public Integer getMember_level_id() {
        return member_level_id;
    }

    public void setMember_level_id(Integer member_level_id) {
        this.member_level_id = member_level_id;
    }

    public String getEmail() {
        if (email == null){
            return "";
        }
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser_qrcode_url() {
        if (user_qrcode_url == null){
            return "";
        }
        return user_qrcode_url;
    }

    public void setUser_qrcode_url(String user_qrcode_url) {
        this.user_qrcode_url = user_qrcode_url;
    }

    public String getPay_pwd() {
        if (pay_pwd == null){
            return "";
        }
        return pay_pwd;
    }

    public void setPay_pwd(String pay_pwd) {
        this.pay_pwd = pay_pwd;
    }

    public Integer getIs_microfinance() {
        return is_microfinance;
    }

    public void setIs_microfinance(Integer is_microfinance) {
        this.is_microfinance = is_microfinance;
    }

    public Double getConsumption_integral() {
        return consumption_integral;
    }

    public void setConsumption_integral(Double consumption_integral) {
        this.consumption_integral = consumption_integral;
    }

    public Double getGeneral_integral() {
        return general_integral;
    }

    public void setGeneral_integral(Double general_integral) {
        this.general_integral = general_integral;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    public Integer getMember_star() {
        return member_star;
    }

    public void setMember_star(Integer member_star) {
        this.member_star = member_star;
    }
}

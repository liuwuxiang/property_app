package com.springmvc.entity;

import java.util.Date;

public class WnkBusinessRegister {
    private Integer id;
    private String store_name;
    private Integer type_id;
    private String area;
    private String address;
    private String login_account;
    private String contact_name;
    private String contact_mobile;
    private String miaoshu;
    private String yingye_zhizhao_photo;
    private String mentou_photo;
    private Integer state;
    private Date submit_date;
    private Integer recommend_business_id;
    private String legal_person_id_card;

    public String getLegal_person_id_card() {
        return legal_person_id_card;
    }

    public void setLegal_person_id_card(String legal_person_id_card) {
        this.legal_person_id_card = legal_person_id_card;
    }

    public Integer getRecommend_business_id() {
        return recommend_business_id;
    }

    public void setRecommend_business_id(Integer recommend_business_id) {
        this.recommend_business_id = recommend_business_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin_account() {
        return login_account;
    }

    public void setLogin_account(String login_account) {
        this.login_account = login_account;
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

    public String getMiaoshu() {
        return miaoshu;
    }

    public void setMiaoshu(String miaoshu) {
        this.miaoshu = miaoshu;
    }

    public String getYingye_zhizhao_photo() {
        return yingye_zhizhao_photo;
    }

    public void setYingye_zhizhao_photo(String yingye_zhizhao_photo) {
        this.yingye_zhizhao_photo = yingye_zhizhao_photo;
    }

    public String getMentou_photo() {
        return mentou_photo;
    }

    public void setMentou_photo(String mentou_photo) {
        this.mentou_photo = mentou_photo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(Date submit_date) {
        this.submit_date = submit_date;
    }
}

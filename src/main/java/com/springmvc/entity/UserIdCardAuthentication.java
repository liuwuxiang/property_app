package com.springmvc.entity;

import java.util.Date;

public class UserIdCardAuthentication {
    private Integer id;
    private String mobile;
    private String real_name;
    private String id_card_number;
    private Date card_effective_deadline;
    private String handheld_identity_card_photo;
    private Integer user_id;
    private Date submit_date;
    private Date authentication_finish_date;
    private Integer state;

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

    public Date getCard_effective_deadline() {
        return card_effective_deadline;
    }

    public void setCard_effective_deadline(Date card_effective_deadline) {
        this.card_effective_deadline = card_effective_deadline;
    }

    public String getHandheld_identity_card_photo() {
        return handheld_identity_card_photo;
    }

    public void setHandheld_identity_card_photo(String handheld_identity_card_photo) {
        this.handheld_identity_card_photo = handheld_identity_card_photo;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Date getSubmit_date() {
        return submit_date;
    }

    public void setSubmit_date(Date submit_date) {
        this.submit_date = submit_date;
    }

    public Date getAuthentication_finish_date() {
        return authentication_finish_date;
    }

    public void setAuthentication_finish_date(Date authentication_finish_date) {
        this.authentication_finish_date = authentication_finish_date;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

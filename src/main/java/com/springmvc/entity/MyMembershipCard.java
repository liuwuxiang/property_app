package com.springmvc.entity;

import java.util.Date;

public class MyMembershipCard {
    private Integer id;
    private Integer business_id;
    private Integer fold_number;
    private Date term_validity;
    private String member_no;
    private Integer user_id;
    private String qrcode_url;
    private Date get_time;

    public Date getGet_time() {
        return get_time;
    }

    public void setGet_time(Date get_time) {
        this.get_time = get_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getFold_number() {
        return fold_number;
    }

    public void setFold_number(Integer fold_number) {
        this.fold_number = fold_number;
    }

    public Date getTerm_validity() {
        return term_validity;
    }

    public void setTerm_validity(Date term_validity) {
        this.term_validity = term_validity;
    }

    public String getMember_no() {
        return member_no;
    }

    public void setMember_no(String member_no) {
        this.member_no = member_no;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getQrcode_url() {
        return qrcode_url;
    }

    public void setQrcode_url(String qrcode_url) {
        this.qrcode_url = qrcode_url;
    }
}

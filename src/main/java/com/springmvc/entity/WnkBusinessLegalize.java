package com.springmvc.entity;

import java.sql.Timestamp;

/**
 * @author 杨新杰
 * @Date 2018/11/7 16:17
 */
public class WnkBusinessLegalize {
    private Integer id;
    private Integer business_id;
    private Integer legalize_status;
    private Timestamp start_time;
    private Timestamp stop_time;
    private String id_card_facade_img;
    private String id_card_rear_img;
    private String business_license_img;
    private String license_img;
    private String phone;

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

    public Integer getLegalize_status() {
        return legalize_status;
    }

    public void setLegalize_status(Integer legalize_status) {
        this.legalize_status = legalize_status;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getStop_time() {
        return stop_time;
    }

    public void setStop_time(Timestamp stop_time) {
        this.stop_time = stop_time;
    }

    public String getId_card_facade_img() {
        return id_card_facade_img;
    }

    public void setId_card_facade_img(String id_card_facade_img) {
        this.id_card_facade_img = id_card_facade_img;
    }

    public String getId_card_rear_img() {
        return id_card_rear_img;
    }

    public void setId_card_rear_img(String id_card_rear_img) {
        this.id_card_rear_img = id_card_rear_img;
    }

    public String getBusiness_license_img() {
        return business_license_img;
    }

    public void setBusiness_license_img(String business_license_img) {
        this.business_license_img = business_license_img;
    }

    public String getLicense_img() {
        return license_img;
    }

    public void setLicense_img(String license_img) {
        this.license_img = license_img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

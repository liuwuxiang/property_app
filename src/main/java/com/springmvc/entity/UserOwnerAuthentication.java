package com.springmvc.entity;

import java.util.Date;

public class UserOwnerAuthentication {

    private Integer id;
    private String buy_house_mobile;
    private String buy_house_name;
    private Integer province_id;
    private Integer city_id;
    private Integer residential_id;
    private Integer residential_building_id;
    private Integer residential_unit_id;
    private Integer house_number_id;
    private Integer user_id;
    private Date submit_date;
    private Date authentication_finish_date;
    private Integer state;
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuy_house_mobile() {
        return buy_house_mobile;
    }

    public void setBuy_house_mobile(String buy_house_mobile) {
        this.buy_house_mobile = buy_house_mobile;
    }

    public String getBuy_house_name() {
        return buy_house_name;
    }

    public void setBuy_house_name(String buy_house_name) {
        this.buy_house_name = buy_house_name;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getResidential_id() {
        return residential_id;
    }

    public void setResidential_id(Integer residential_id) {
        this.residential_id = residential_id;
    }

    public Integer getResidential_building_id() {
        return residential_building_id;
    }

    public void setResidential_building_id(Integer residential_building_id) {
        this.residential_building_id = residential_building_id;
    }

    public Integer getResidential_unit_id() {
        return residential_unit_id;
    }

    public void setResidential_unit_id(Integer residential_unit_id) {
        this.residential_unit_id = residential_unit_id;
    }

    public Integer getHouse_number_id() {
        return house_number_id;
    }

    public void setHouse_number_id(Integer house_number_id) {
        this.house_number_id = house_number_id;
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

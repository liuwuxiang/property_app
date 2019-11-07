package com.springmvc.entity;

import java.util.Date;

public class Residentials {

    private Integer id;
    private Integer province_id;
    private Integer city_id;
    private String residential_name;
    private Date create_time;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getResidential_name() {
        return residential_name;
    }

    public void setResidential_name(String residential_name) {
        this.residential_name = residential_name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}

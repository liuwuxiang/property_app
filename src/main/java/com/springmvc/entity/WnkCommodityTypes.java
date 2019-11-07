package com.springmvc.entity;

public class WnkCommodityTypes {
    private Integer id;
    private String type_name;
    private Integer business_id;
    private Integer state;
    private Integer delete_state;

    public Integer getDelete_state() {
        return delete_state;
    }

    public void setDelete_state(Integer delete_state) {
        this.delete_state = delete_state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

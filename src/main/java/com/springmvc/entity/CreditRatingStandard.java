package com.springmvc.entity;

public class CreditRatingStandard {
    private Integer id;
    private Integer clasp_object;
    private String clasp_type_name;
    private Integer clasp_value;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClasp_object() {
        return clasp_object;
    }

    public void setClasp_object(Integer clasp_object) {
        this.clasp_object = clasp_object;
    }

    public String getClasp_type_name() {
        return clasp_type_name;
    }

    public void setClasp_type_name(String clasp_type_name) {
        this.clasp_type_name = clasp_type_name;
    }

    public Integer getClasp_value() {
        return clasp_value;
    }

    public void setClasp_value(Integer clasp_value) {
        this.clasp_value = clasp_value;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

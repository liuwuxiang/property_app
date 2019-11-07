package com.springmvc.entity;

public class AdminTypes {
    private Integer id;
    private String type_name;
    private String access_rights;

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

    public String getAccess_rights() {
        return access_rights;
    }

    public void setAccess_rights(String access_rights) {
        this.access_rights = access_rights;
    }
}

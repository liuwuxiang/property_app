package com.springmvc.entity;

public class BackstageMenus {
    private Integer id;
    private String name;
    private Integer type;
    private String menus_url;
    private Integer last_level_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMenus_url() {
        return menus_url;
    }

    public void setMenus_url(String menus_url) {
        this.menus_url = menus_url;
    }

    public Integer getLast_level_id() {
        return last_level_id;
    }

    public void setLast_level_id(Integer last_level_id) {
        this.last_level_id = last_level_id;
    }
}

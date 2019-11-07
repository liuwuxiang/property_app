package com.springmvc.entity;

public class ProvinceAndCity {
    private Integer id;
    private String name;
    private Integer type;
    private Integer upper_level_id;

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

    public Integer getUpper_level_id() {
        return upper_level_id;
    }

    public void setUpper_level_id(Integer upper_level_id) {
        this.upper_level_id = upper_level_id;
    }
}

package com.springmvc.entity;

public class MemberLevels {

    private Integer id;
    private String icon_url;
    private String name;
    private String level_introduction;
    private Integer is_default_level;
    private String brief_introduction;
    private Integer recharge_consumption_integral;
    private Integer is_del;

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }


    public Integer getRecharge_consumption_integral() {
        return recharge_consumption_integral;
    }

    public void setRecharge_consumption_integral(Integer recharge_consumption_integral) {
        this.recharge_consumption_integral = recharge_consumption_integral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel_introduction() {
        if (level_introduction == null){
            return "";
        }
        return level_introduction;
    }

    public void setLevel_introduction(String level_introduction) {
        this.level_introduction = level_introduction;
    }

    public Integer getIs_default_level() {
        return is_default_level;
    }

    public void setIs_default_level(Integer is_default_level) {
        this.is_default_level = is_default_level;
    }

    public String getBrief_introduction() {
        return brief_introduction;
    }

    public void setBrief_introduction(String brief_introduction) {
        this.brief_introduction = brief_introduction;
    }
}

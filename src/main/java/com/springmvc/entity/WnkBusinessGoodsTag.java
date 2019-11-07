package com.springmvc.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author 杨新杰
 * @Date 2018/11/17 14:23
 */
public class WnkBusinessGoodsTag {
    private Integer id;
    private String name;
    private Integer state;
    private Timestamp create_time;
    private Integer business_id;

    @Override
    public String
    toString() {
        return "WnkBusinessGoodsTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", create_time=" + create_time +
                ", business_id=" + business_id +
                '}';
    }

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }
}

package com.springmvc.entity;


import java.util.Date;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/29 17:15
 */
public class WnkBusinessFitnessRecord {
    private int id;
    private Integer user_id;
    private Integer business_id;
    private Date use_time;
    private Integer use_type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }


    public Date getUse_time() {
        return use_time;
    }

    public void setUse_time(Date use_time) {
        this.use_time = use_time;
    }

    public Integer getUse_type() {
        return use_type;
    }

    public void setUse_type(Integer use_type) {
        this.use_type = use_type;
    }
}

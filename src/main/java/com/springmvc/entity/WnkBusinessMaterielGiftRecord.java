package com.springmvc.entity;

import java.util.Date;

/**
 * 功能描述: 赠送物料记录表.
 *
 * @author 杨新杰
 * @date 2019/1/5 11:18
 */
public class WnkBusinessMaterielGiftRecord {
    private int id;
    private Integer admin_id;
    private Date gift_time;
    private Integer business_id;
    private Integer gift_before_number;
    private Integer gift_after_number;
    private Integer materiel_id;
    private Integer gift_number;
    private Integer back_number;
    private Date back_time;
    private Integer back_status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public Date getGift_time() {
        return gift_time;
    }

    public void setGift_time(Date gift_time) {
        this.gift_time = gift_time;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getGift_before_number() {
        return gift_before_number;
    }

    public void setGift_before_number(Integer gift_before_number) {
        this.gift_before_number = gift_before_number;
    }

    public Integer getGift_after_number() {
        return gift_after_number;
    }

    public void setGift_after_number(Integer gift_after_number) {
        this.gift_after_number = gift_after_number;
    }

    public Integer getMateriel_id() {
        return materiel_id;
    }

    public void setMateriel_id(Integer materiel_id) {
        this.materiel_id = materiel_id;
    }

    public Integer getGift_number() {
        return gift_number;
    }

    public void setGift_number(Integer gift_number) {
        this.gift_number = gift_number;
    }

    public Integer getBack_number() {
        return back_number;
    }

    public void setBack_number(Integer back_number) {
        this.back_number = back_number;
    }

    public Date getBack_time() {
        return back_time;
    }

    public void setBack_time(Date back_time) {
        this.back_time = back_time;
    }

    public Integer getBack_status() {
        return back_status;
    }

    public void setBack_status(Integer back_status) {
        this.back_status = back_status;
    }
}

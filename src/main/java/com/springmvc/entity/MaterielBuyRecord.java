package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/11/18 00:25
 * @Description:商家物料购买记录实体类
 */
public class MaterielBuyRecord {
    private Integer id;
    private Integer materiel_id;
    private Integer business_id;
    private Integer buy_number;
    private Date buy_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMateriel_id() {
        return materiel_id;
    }

    public void setMateriel_id(Integer materiel_id) {
        this.materiel_id = materiel_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getBuy_number() {
        return buy_number;
    }

    public void setBuy_number(Integer buy_number) {
        this.buy_number = buy_number;
    }

    public Date getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(Date buy_date) {
        this.buy_date = buy_date;
    }
}

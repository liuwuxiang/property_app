package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 03:02
 * @Description:商家购买的物料信息实体类
 */
public class MyMateriel {
    private Integer id;
    private Integer business_id;
    private Integer materiel_id;
    private Integer surplus_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getMateriel_id() {
        return materiel_id;
    }

    public void setMateriel_id(Integer materiel_id) {
        this.materiel_id = materiel_id;
    }

    public Integer getSurplus_number() {
        return surplus_number;
    }

    public void setSurplus_number(Integer surplus_number) {
        this.surplus_number = surplus_number;
    }
}

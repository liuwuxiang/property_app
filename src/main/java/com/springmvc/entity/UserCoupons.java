package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 05:45
 * @Description:用户优惠券实体类
 */
public class UserCoupons {
    private Integer id;
    private Integer user_id;
    private Integer materiel_id;
    private Integer surplus_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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

package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 03:24
 * @Description:物料购买套餐实体信息
 */
public class ExtensionMaterielBuyMeal {
    private Integer id;
    private Integer materiel_id;
    private Double price;
    private Integer number;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}

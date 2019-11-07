package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 16:11
 * @Description:2.0版本商品订单订单商品记录实体类
 */
public class WnkOrderCommodityTwo {
    private Integer id;
    private Integer order_id;
    private Integer commodity_id;
    private Integer buy_number;
    private Double count_amount;
    private Integer commodity_guige_id;
    private Integer already_used_number;
    private Integer refunded_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public Integer getBuy_number() {
        return buy_number;
    }

    public void setBuy_number(Integer buy_number) {
        this.buy_number = buy_number;
    }

    public Double getCount_amount() {
        return count_amount;
    }

    public void setCount_amount(Double count_amount) {
        this.count_amount = count_amount;
    }

    public Integer getCommodity_guige_id() {
        return commodity_guige_id;
    }

    public void setCommodity_guige_id(Integer commodity_guige_id) {
        this.commodity_guige_id = commodity_guige_id;
    }

    public Integer getAlready_used_number() {
        return already_used_number;
    }

    public void setAlready_used_number(Integer already_used_number) {
        this.already_used_number = already_used_number;
    }

    public Integer getRefunded_number() {
        return refunded_number;
    }

    public void setRefunded_number(Integer refunded_number) {
        this.refunded_number = refunded_number;
    }
}

package com.springmvc.entity;

/**
 *
 * 万能卡分类商家扩展管理实体类(酒店类)
 *
 * @author 杨新杰
 *
 */
public class WnkBusinessTypeManagementHotel {
    private int id;
    private Integer business_id;
    private Integer commodities_id;
    private Long    time_stamp;
    private Integer inventory_num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getCommodities_id() {
        return commodities_id;
    }

    public void setCommodities_id(Integer commodities_id) {
        this.commodities_id = commodities_id;
    }

    public Long getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Long time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Integer getInventory_num() {
        return inventory_num;
    }

    public void setInventory_num(Integer inventory_num) {
        this.inventory_num = inventory_num;
    }
}

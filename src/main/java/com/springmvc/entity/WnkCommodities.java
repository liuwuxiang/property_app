package com.springmvc.entity;

import java.util.Date;

/**
 * 万能卡商品实体类
 * @author 杨新杰
 */
public class WnkCommodities {
    private Integer id;
    /**商品名称*/
    private String  name;
    /**商品描述*/
    private String  commodity_describe;
    /**商品价格*/
    private Double  price;
    /**商品状态(0-上架,1-下架,2-删除)*/
    private Integer state;
    /**商品图片id*/
    private String  photo;
    /**商家ID*/
    private Integer business_id;
    /**商品分类id(来自wnk_commodity_types表中的id)*/
    private Integer type_id;
    /**加入时间*/
    private Date    join_time;
    /**商品销量*/
    private Integer sales_volume;
    /**是否可以使用万能卡(0-不可以,1-可以)*/
    private Integer is_make_wnk;
    /**商品标签,多个标签用逗号隔开*/
    private String  goods_tag;
    /**删除状态(0-未删除,1-已删除)*/
    private Integer delete_state;

    public Integer getDelete_state() {
        return delete_state;
    }

    public void setDelete_state(Integer delete_state) {
        this.delete_state = delete_state;
    }

    public String getGoods_tag() {
        return goods_tag;
    }

    public void setGoods_tag(String goods_tag) {
        this.goods_tag = goods_tag;
    }

    public Integer getIs_make_wnk() {
        return is_make_wnk;
    }

    public void setIs_make_wnk(Integer is_make_wnk) {
        this.is_make_wnk = is_make_wnk;
    }

    public Integer getSales_volume() {
        return sales_volume;
    }

    public void setSales_volume(Integer sales_volume) {
        this.sales_volume = sales_volume;
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

    public String getCommodity_describe() {
        return commodity_describe;
    }

    public void setCommodity_describe(String commodity_describe) {
        this.commodity_describe = commodity_describe;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }
}

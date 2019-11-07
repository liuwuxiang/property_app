package com.springmvc.entity;

/**
 *
 * 万能卡商家商品规格实体类
 *
 * @author 杨新杰
 */
public class WnkCommoditySpecifications {
    /**记录ID*/
    private Integer id;
    /**商品id*/
    private Integer commodity_id;
    /**规格名称*/
    private String  specifications_name;
    /**状态(0-启用,1-停用)*/
    private Integer state;
    /**价格*/
    private Double  price;
    /**库存数量(-1为不限)*/
    private Integer inventory;
    /**是否启用万能卡 0 启用 1 禁用*/
    private Integer is_wnk;
    /**消费积分赠送比例 is_wnk - 1 的时候生效*/
    private Integer gift_noun;

    public Integer getGift_noun() {
        return gift_noun;
    }

    public void setGift_noun(Integer gift_noun) {
        this.gift_noun = gift_noun;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Integer getIs_wnk() {
        return is_wnk;
    }

    public void setIs_wnk(Integer is_wnk) {
        this.is_wnk = is_wnk;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(Integer commodity_id) {
        this.commodity_id = commodity_id;
    }

    public String getSpecifications_name() {
        return specifications_name;
    }

    public void setSpecifications_name(String specifications_name) {
        this.specifications_name = specifications_name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}

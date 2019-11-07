package com.springmvc.entity;

public class WnkBusinessType {
    private Integer id;
    private String  name;
    private String  background_photo_id;
    private String  logo_photo_id;
    private Integer month_free_number;
    private Integer commdity_charge_way;
    private Double  commodifty_price;
    private Integer make_wnk_state;
    private Integer discount_type;
    private Integer is_del;
    private Integer is_limit;
    private Integer limit_number;

    public Integer getIs_limit() {
        return is_limit;
    }

    public void setIs_limit(Integer is_limit) {
        this.is_limit = is_limit;
    }

    public Integer getLimit_number() {
        return limit_number;
    }

    public void setLimit_number(Integer limit_number) {
        this.limit_number = limit_number;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public Integer getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(Integer discount_type) {
        this.discount_type = discount_type;
    }

    public Integer getMake_wnk_state() {
        return make_wnk_state;
    }

    public void setMake_wnk_state(Integer make_wnk_state) {
        this.make_wnk_state = make_wnk_state;
    }

    public Integer getCommdity_charge_way() {
        return commdity_charge_way;
    }

    public void setCommdity_charge_way(Integer commdity_charge_way) {
        this.commdity_charge_way = commdity_charge_way;
    }

    public Double getCommodifty_price() {
        return commodifty_price;
    }

    public void setCommodifty_price(Double commodifty_price) {
        this.commodifty_price = commodifty_price;
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

    public String getBackground_photo_id() {
        return background_photo_id;
    }

    public void setBackground_photo_id(String background_photo_id) {
        this.background_photo_id = background_photo_id;
    }

    public String getLogo_photo_id() {
        return logo_photo_id;
    }

    public void setLogo_photo_id(String logo_photo_id) {
        this.logo_photo_id = logo_photo_id;
    }

    public Integer getMonth_free_number() {
        return month_free_number;
    }

    public void setMonth_free_number(Integer month_free_number) {
        this.month_free_number = month_free_number;
    }
}

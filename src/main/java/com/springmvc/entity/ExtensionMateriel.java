package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/30 02:23
 * @Description:推广物料(用于供商家选择)实体类
 */
public class ExtensionMateriel {
    private Integer id;
    private String name;
    private String logo_photo_id;
    private String background_photo;
    private Integer business_type_id;
    private Integer is_wnk;
    private Integer limit_times;
    private Integer buy_number;

    public Integer getLimit_times() {
        return limit_times;
    }

    public void setLimit_times(Integer limit_times) {
        this.limit_times = limit_times;
    }

    public Integer getBuy_number() {
        return buy_number;
    }

    public void setBuy_number(Integer buy_number) {
        this.buy_number = buy_number;
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

    public String getLogo_photo_id() {
        return logo_photo_id;
    }

    public void setLogo_photo_id(String logo_photo_id) {
        this.logo_photo_id = logo_photo_id;
    }

    public String getBackground_photo() {
        return background_photo;
    }

    public void setBackground_photo(String background_photo) {
        this.background_photo = background_photo;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }

    public Integer getIs_wnk() {
        return is_wnk;
    }

    public void setIs_wnk(Integer is_wnk) {
        this.is_wnk = is_wnk;
    }
}

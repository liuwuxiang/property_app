package com.springmvc.entity;


import java.sql.Timestamp;

/**
 * 万能卡商家积分商城-商品实体类
 * @author 杨新杰
 * @Date 2018/10/11 14:47
 */
public class WnkIntegralGoods {
    private Integer id;
    private Integer business_id;
    private String name;
    private Integer price;
    private String img;
    private String detail;
    private Integer type;
    private Integer is_checked;
    private String synopsis;
    private Timestamp creation_time;
    private Integer is_recommend;

    public Integer getIs_recommend() {
        return is_recommend;
    }

    public void setIs_recommend(Integer is_recommend) {
        this.is_recommend = is_recommend;
    }

    @Override
    public String toString() {
        return "WnkIntegralGoods{" +
                "id=" + id +
                ", businessId=" + business_id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", img='" + img + '\'' +
                ", detail='" + detail + '\'' +
                ", type=" + type +
                ", is_checked=" + is_checked +
                ", synopsis='" + synopsis + '\'' +
                ", creation_time=" + creation_time +
                '}';
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer businessId) {
        this.business_id = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(Integer isChecked) {
        this.is_checked = isChecked;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

}

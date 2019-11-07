package com.springmvc.entity;

public class IntegralGoods {

    private Integer id;
    private String name;
    private Double price;
    private String trader;
    private String detail;
    private String img;
    private String isRecommend;
    private Integer type;
    private String synopsis;
    private Integer is_checked;

    public Integer getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(Integer is_checked) {
        this.is_checked = is_checked;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Override
    public String toString() {
        return "IntegralGoods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", trader='" + trader + '\'' +
                ", detail='" + detail + '\'' +
                ", img='" + img + '\'' +
                ", isRecommend='" + isRecommend + '\'' +
                ", type='" + type + '\'' +
                ", synopsis='" + synopsis + '\'' +
                '}';
    }
}

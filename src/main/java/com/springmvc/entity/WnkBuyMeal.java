package com.springmvc.entity;

public class WnkBuyMeal {
    private Integer id;
    private String name;
    private Integer price;
    private Integer duration_validity_period;
    private Integer type;
    private Integer wnk_make_number;
    private Integer card_type;

    public Integer getCard_type() {
        return card_type;
    }

    public void setCard_type(Integer card_type) {
        this.card_type = card_type;
    }

    public Integer getWnk_make_number() {
        return wnk_make_number;
    }

    public void setWnk_make_number(Integer wnk_make_number) {
        this.wnk_make_number = wnk_make_number;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDuration_validity_period() {
        return duration_validity_period;
    }

    public void setDuration_validity_period(Integer duration_validity_period) {
        this.duration_validity_period = duration_validity_period;
    }
}

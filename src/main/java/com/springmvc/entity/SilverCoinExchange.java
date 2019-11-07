package com.springmvc.entity;

import java.util.Date;

public class SilverCoinExchange {
    private Integer id;
    private Integer consume_integral_number;
    private Integer type;
    private Date exchange_date;
    private String order_no;
    private Integer user_id;
    private Integer expenditure_id;
    private Integer silver_coin_detail_id;
    private Integer silver_coin_number;

    public Integer getSilver_coin_number() {
        return silver_coin_number;
    }

    public void setSilver_coin_number(Integer silver_coin_number) {
        this.silver_coin_number = silver_coin_number;
    }

    public Integer getSilver_coin_detail_id() {
        return silver_coin_detail_id;
    }

    public void setSilver_coin_detail_id(Integer silver_coin_detail_id) {
        this.silver_coin_detail_id = silver_coin_detail_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsume_integral_number() {
        return consume_integral_number;
    }

    public void setConsume_integral_number(Integer consume_integral_number) {
        this.consume_integral_number = consume_integral_number;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getExchange_date() {
        return exchange_date;
    }

    public void setExchange_date(Date exchange_date) {
        this.exchange_date = exchange_date;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getExpenditure_id() {
        return expenditure_id;
    }

    public void setExpenditure_id(Integer expenditure_id) {
        this.expenditure_id = expenditure_id;
    }
}

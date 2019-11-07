package com.springmvc.entity;

public class UsersBankCard {
    private Integer id;
    private Integer bank_id;
    private String bank_number;
    private String bank_card_name;
    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBank_id() {
        return bank_id;
    }

    public void setBank_id(Integer bank_id) {
        this.bank_id = bank_id;
    }

    public String getBank_number() {
        return bank_number;
    }

    public void setBank_number(String bank_number) {
        this.bank_number = bank_number;
    }

    public String getBank_card_name() {
        return bank_card_name;
    }

    public void setBank_card_name(String bank_card_name) {
        this.bank_card_name = bank_card_name;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}

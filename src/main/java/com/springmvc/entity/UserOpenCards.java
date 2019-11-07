package com.springmvc.entity;

import java.util.Date;

public class UserOpenCards {
    private Integer id;
    private Integer user_id;
    private Integer card_type_id;
    private String card_no;
    private Date open_card_date;
    private Date term_validity;
    private Integer user_type;

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCard_type_id() {
        return card_type_id;
    }

    public void setCard_type_id(Integer card_type_id) {
        this.card_type_id = card_type_id;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public Date getOpen_card_date() {
        return open_card_date;
    }

    public void setOpen_card_date(Date open_card_date) {
        this.open_card_date = open_card_date;
    }

    public Date getTerm_validity() {
        return term_validity;
    }

    public void setTerm_validity(Date term_validity) {
        this.term_validity = term_validity;
    }
}

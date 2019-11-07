package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 00:46
 * @Description:万能卡商家银行卡信息实体类
 */
public class WnkBusinessBankCard {
    private Integer id;
    private Integer bank_id;
    private String bank_number;
    private String bank_card_name;
    private Integer business_id;

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

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }
}

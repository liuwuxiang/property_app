package com.springmvc.entity;

import java.util.Date;

public class UsersWithdrawOrder {
    private Integer id;
    private Integer expenditure_integral;
    private Double rmb_count_amount;
    private Double real_payment_rmb_amount;
    private String back_name;
    private String back_code;
    private String bank_card_number;
    private String bank_card_name;
    private Integer formality_rate;
    private Double service_charge_amount;
    private Date apply_date;
    private Date loan_date;
    private Integer user_id;
    private Integer state;
    private String remark;
    private String order_no;
    private String out_trade_no;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getExpenditure_integral() {
        return expenditure_integral;
    }

    public void setExpenditure_integral(Integer expenditure_integral) {
        this.expenditure_integral = expenditure_integral;
    }

    public Double getRmb_count_amount() {
        return rmb_count_amount;
    }

    public void setRmb_count_amount(Double rmb_count_amount) {
        this.rmb_count_amount = rmb_count_amount;
    }

    public Double getReal_payment_rmb_amount() {
        return real_payment_rmb_amount;
    }

    public void setReal_payment_rmb_amount(Double real_payment_rmb_amount) {
        this.real_payment_rmb_amount = real_payment_rmb_amount;
    }

    public String getBack_name() {
        return back_name;
    }

    public void setBack_name(String back_name) {
        this.back_name = back_name;
    }

    public String getBack_code() {
        return back_code;
    }

    public void setBack_code(String back_code) {
        this.back_code = back_code;
    }

    public String getBank_card_number() {
        return bank_card_number;
    }

    public void setBank_card_number(String bank_card_number) {
        this.bank_card_number = bank_card_number;
    }

    public String getBank_card_name() {
        return bank_card_name;
    }

    public void setBank_card_name(String bank_card_name) {
        this.bank_card_name = bank_card_name;
    }

    public Integer getFormality_rate() {
        return formality_rate;
    }

    public void setFormality_rate(Integer formality_rate) {
        this.formality_rate = formality_rate;
    }

    public Double getService_charge_amount() {
        return service_charge_amount;
    }

    public void setService_charge_amount(Double service_charge_amount) {
        this.service_charge_amount = service_charge_amount;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public Date getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(Date loan_date) {
        this.loan_date = loan_date;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}

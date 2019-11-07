package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 16:06
 * @Description:2.0版本商品订单记录实体类
 */
public class WnkOrdersTwo {
    private Integer id;
    private Integer business_id;
    private Integer user_id;
    private String order_no;
    private Date submit_time;
    private Date pay_time;
    private Double amount;
    private Integer pay_way;
    private Integer state;
    private String order_qrcode;
    private Double general_integral;
    private Double send_integral;
    private Integer coupon;
    private Double cash_amount;
    private Integer order_type;


    public Integer getOrder_type() {
        return order_type;
    }

    public void setOrder_type(Integer order_type) {
        this.order_type = order_type;
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

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getSubmit_time() {
        return submit_time;
    }

    public void setSubmit_time(Date submit_time) {
        this.submit_time = submit_time;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPay_way() {
        return pay_way;
    }

    public void setPay_way(Integer pay_way) {
        this.pay_way = pay_way;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getOrder_qrcode() {
        return order_qrcode;
    }

    public void setOrder_qrcode(String order_qrcode) {
        this.order_qrcode = order_qrcode;
    }

    public Double getGeneral_integral() {
        return general_integral;
    }

    public void setGeneral_integral(Double general_integral) {
        this.general_integral = general_integral;
    }

    public Double getSend_integral() {
        return send_integral;
    }

    public void setSend_integral(Double send_integral) {
        this.send_integral = send_integral;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Double getCash_amount() {
        return cash_amount;
    }

    public void setCash_amount(Double cash_amount) {
        this.cash_amount = cash_amount;
    }

    @Override
    public String toString() {
        return "WnkOrdersTwo{" +
                "id=" + id +
                ", business_id=" + business_id +
                ", user_id=" + user_id +
                ", order_no='" + order_no + '\'' +
                ", submit_time=" + submit_time +
                ", pay_time=" + pay_time +
                ", amount=" + amount +
                ", pay_way=" + pay_way +
                ", state=" + state +
                ", order_qrcode='" + order_qrcode + '\'' +
                ", general_integral=" + general_integral +
                ", send_integral=" + send_integral +
                ", coupon=" + coupon +
                ", cash_amount=" + cash_amount +
                ", order_type=" + order_type +
                '}';
    }
}

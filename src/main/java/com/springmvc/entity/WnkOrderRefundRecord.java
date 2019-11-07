package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 16:16
 * @Description:2.0版本商品订单退款记实体类
 */
public class WnkOrderRefundRecord {
    private Integer id;
    private String refund_no;
    private Integer refund_number;
    private Double general_integral;
    private String order_no;
    private Date refund_date;
    private Integer coupon;
    private Double cash;
    private Double send_integral;
    private String refund_reason;

    public String getRefund_reason() {
        return refund_reason;
    }

    public void setRefund_reason(String refund_reason) {
        this.refund_reason = refund_reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefund_no() {
        return refund_no;
    }

    public void setRefund_no(String refund_no) {
        this.refund_no = refund_no;
    }

    public Integer getRefund_number() {
        return refund_number;
    }

    public void setRefund_number(Integer refund_number) {
        this.refund_number = refund_number;
    }

    public Double getGeneral_integral() {
        return general_integral;
    }

    public void setGeneral_integral(Double general_integral) {
        this.general_integral = general_integral;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getRefund_date() {
        return refund_date;
    }

    public void setRefund_date(Date refund_date) {
        this.refund_date = refund_date;
    }

    public Integer getCoupon() {
        return coupon;
    }

    public void setCoupon(Integer coupon) {
        this.coupon = coupon;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getSend_integral() {
        return send_integral;
    }

    public void setSend_integral(Double send_integral) {
        this.send_integral = send_integral;
    }
}

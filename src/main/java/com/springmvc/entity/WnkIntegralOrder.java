package com.springmvc.entity;

import java.sql.Timestamp;

/**
 * 商家积分商城 - 订单实体类
 * @author 杨新杰
 * @Date 2018/10/10 12:11
 */
public class WnkIntegralOrder {

    private Integer id;
    private String order_id;
    private Integer goods_id;
    private Integer user_id;
    private Integer business_id;
    private Integer price;
    private Integer status;
    private String username;
    private String phone;
    private Timestamp creation_time;
    private Timestamp end_time;
    private String qrcode;

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }


    @Override
    public String toString() {
        return "WnkIntegralOrder{" +
                "id=" + id +
                ", order_id=" + order_id +
                ", goods_id=" + goods_id +
                ", user_id=" + user_id +
                ", business_id=" + business_id +
                ", price=" + price +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", creation_time=" + creation_time +
                ", end_time=" + end_time +
                ", qrQucode=" + qrcode +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WnkIntegralOrder that = (WnkIntegralOrder) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (order_id != null ? !order_id.equals(that.order_id) : that.order_id != null) return false;
        if (goods_id != null ? !goods_id.equals(that.goods_id) : that.goods_id != null) return false;
        if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
        if (business_id != null ? !business_id.equals(that.business_id) : that.business_id != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (creation_time != null ? !creation_time.equals(that.creation_time) : that.creation_time != null) return false;
        if (end_time != null ? !end_time.equals(that.end_time) : that.end_time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (order_id != null ? order_id.hashCode() : 0);
        result = 31 * result + (goods_id != null ? goods_id.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (business_id != null ? business_id.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (creation_time != null ? creation_time.hashCode() : 0);
        result = 31 * result + (end_time != null ? end_time.hashCode() : 0);
        return result;
    }

}

package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/12/8 16:19
 * @Description:2.0版本商品使用记录实体类
 */
public class WnkOrderMakeRecord {
    private Integer id;
    private String order_no;
    private Date make_date;
    private Integer make_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public Date getMake_date() {
        return make_date;
    }

    public void setMake_date(Date make_date) {
        this.make_date = make_date;
    }

    public Integer getMake_number() {
        return make_number;
    }

    public void setMake_number(Integer make_number) {
        this.make_number = make_number;
    }
}

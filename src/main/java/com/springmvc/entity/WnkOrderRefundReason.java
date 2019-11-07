package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/12/10 01:37
 * @Description:2.0版本万能卡订单退款原因选项表实体类
 */
public class WnkOrderRefundReason {
    private Integer id;
    private String reason;
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

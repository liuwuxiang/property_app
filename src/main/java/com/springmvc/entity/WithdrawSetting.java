package com.springmvc.entity;


import java.util.Date;

/**
 * 功能描述: 提现设置实体类
 *
 * @author 杨新杰
 * @date 2018/12/22 11:15
 */
public class WithdrawSetting {
    private int id;
    private Integer service_charge_proportion;
    private Integer is_any_time;
    private String withdraw_start_time;
    private String withdraw_end_time;
    private Integer withdraw_proportion;
    private Integer type;
    private Integer min_number;
    private Integer get_time;

    public Integer getGet_time() {
        return get_time;
    }

    public void setGet_time(Integer get_time) {
        this.get_time = get_time;
    }

    public Integer getMin_number() {
        return min_number;
    }

    public void setMin_number(Integer min_number) {
        this.min_number = min_number;
    }

    @Override
    public String toString() {
        return "WithdrawSetting{" +
                "id=" + id +
                ", service_charge_proportion=" + service_charge_proportion +
                ", is_any_time=" + is_any_time +
                ", withdraw_start_time=" + withdraw_start_time +
                ", withdraw_end_time=" + withdraw_end_time +
                ", withdraw_proportion=" + withdraw_proportion +
                ", type=" + type +
                '}';
    }

    public Integer getWithdraw_proportion() {
        return withdraw_proportion;
    }

    public void setWithdraw_proportion(Integer withdraw_proportion) {
        this.withdraw_proportion = withdraw_proportion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getService_charge_proportion() {
        return service_charge_proportion;
    }

    public void setService_charge_proportion(Integer service_charge_proportion) {
        this.service_charge_proportion = service_charge_proportion;
    }

    public Integer getIs_any_time() {
        return is_any_time;
    }

    public void setIs_any_time(Integer is_any_time) {
        this.is_any_time = is_any_time;
    }

    public String getWithdraw_start_time() {
        return withdraw_start_time;
    }

    public void setWithdraw_start_time(String withdraw_start_time) {
        this.withdraw_start_time = withdraw_start_time;
    }

    public String getWithdraw_end_time() {
        return withdraw_end_time;
    }

    public void setWithdraw_end_time(String withdraw_end_time) {
        this.withdraw_end_time = withdraw_end_time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

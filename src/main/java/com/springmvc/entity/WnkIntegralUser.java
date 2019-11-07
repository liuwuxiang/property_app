package com.springmvc.entity;


/**
 * 商家积分商城 - 用户积分表
 * @author 杨新杰
 * @Date 2018/10/12 11:45
 */
public class WnkIntegralUser {
    private Integer id;
    private Integer user_id;
    private Integer business_id;
    private Integer integral;


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

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    @Override
    public String toString() {
        return "WnkIntegralUser{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", business_id=" + business_id +
                ", integral=" + integral +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WnkIntegralUser that = (WnkIntegralUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
        if (business_id != null ? !business_id.equals(that.business_id) : that.business_id != null) return false;
        if (integral != null ? !integral.equals(that.integral) : that.integral != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (business_id != null ? business_id.hashCode() : 0);
        result = 31 * result + (integral != null ? integral.hashCode() : 0);
        return result;
    }

}

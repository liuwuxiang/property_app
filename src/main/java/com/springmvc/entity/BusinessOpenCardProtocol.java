package com.springmvc.entity;

import java.util.Objects;

/**
 * 功能描述: 商家会员卡开卡协议模型
 *
 * @author 杨新杰
 * @date 2019/1/2 14:40
 */
public class BusinessOpenCardProtocol {

    private Integer id;
    private String  content;
    private Integer business_id;
    private Integer is_checked;
    private Integer is_del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(Integer is_checked) {
        this.is_checked = is_checked;
    }

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusinessOpenCardProtocol)) return false;
        BusinessOpenCardProtocol that = (BusinessOpenCardProtocol) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getContent(), that.getContent()) &&
                Objects.equals(getBusiness_id(), that.getBusiness_id()) &&
                Objects.equals(getIs_checked(), that.getIs_checked()) &&
                Objects.equals(getIs_del(), that.getIs_del());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getContent(), getBusiness_id(), getIs_checked(), getIs_del());
    }

    @Override
    public String toString() {
        return "BusinessOpenCardProtocol{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", business_id=" + business_id +
                ", is_checked=" + is_checked +
                ", is_del=" + is_del +
                '}';
    }
}

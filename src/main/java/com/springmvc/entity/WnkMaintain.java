package com.springmvc.entity;

import java.sql.Timestamp;

/**
 * @author 杨新杰
 * @Date 2018/11/14 14:03
 */
public class WnkMaintain {
    private Integer id;
    private Integer business_type_id;
    private String maintain_name;
    private String maintain_phone;
    private Timestamp createTime;
    private Integer status;

    @Override
    public String toString() {
        return "WnkMaintain{" +
                "id=" + id +
                ", business_type_id=" + business_type_id +
                ", maintain_name='" + maintain_name + '\'' +
                ", maintain_phone='" + maintain_phone + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
    }

    public String getMaintain_name() {
        return maintain_name;
    }

    public void setMaintain_name(String maintain_name) {
        this.maintain_name = maintain_name;
    }

    public String getMaintain_phone() {
        return maintain_phone;
    }

    public void setMaintain_phone(String maintain_phone) {
        this.maintain_phone = maintain_phone;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

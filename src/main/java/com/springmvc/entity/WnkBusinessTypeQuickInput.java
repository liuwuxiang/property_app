package com.springmvc.entity;


/**
 * 功能描述:
 *
 * @author  杨新杰
 * @date 2019/1/7 14:36
 */
public class WnkBusinessTypeQuickInput {

    private Integer id;
    private String  name;
    private Integer business_type_id;
    private Integer is_checked;
    private Integer is_del;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBusiness_type_id() {
        return business_type_id;
    }

    public void setBusiness_type_id(Integer business_type_id) {
        this.business_type_id = business_type_id;
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
}

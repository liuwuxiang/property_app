package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 00:22
 * @Description:
 */
public class WnkBusinessTag {
    private Integer id;
    private String name;
    private Integer state;
    private Integer type;
    private Integer last_id;
    private String photo_id;
    private Integer sort_index;
    private Integer relation_business_type_id;
    private Integer is_delete;

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getRelation_business_type_id() {
        return relation_business_type_id;
    }

    public void setRelation_business_type_id(Integer relation_business_type_id) {
        this.relation_business_type_id = relation_business_type_id;
    }

    public Integer getSort_index() {
        return sort_index;
    }

    public void setSort_index(Integer sort_index) {
        this.sort_index = sort_index;
    }

    public String getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(String photo_id) {
        this.photo_id = photo_id;
    }

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getLast_id() {
        return last_id;
    }

    public void setLast_id(Integer last_id) {
        this.last_id = last_id;
    }
}

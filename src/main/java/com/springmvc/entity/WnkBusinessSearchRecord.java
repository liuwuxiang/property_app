package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 16:45
 * @Description:商家搜索记录实体类，用于提取热门搜索商家
 */
public class WnkBusinessSearchRecord {
    private Integer id;
    private Integer business_id;
    private Integer search_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getSearch_number() {
        return search_number;
    }

    public void setSearch_number(Integer search_number) {
        this.search_number = search_number;
    }
}

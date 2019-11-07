package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/20 03:06
 * @Description:用于记录万能卡商家选择的标签实体类
 */
public class WnkBusinessTagChooseRecord {
    private Integer id;
    private Integer business_id;
    private Integer one_tag_id;
    private Integer two_tag_id;

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

    public Integer getOne_tag_id() {
        return one_tag_id;
    }

    public void setOne_tag_id(Integer one_tag_id) {
        this.one_tag_id = one_tag_id;
    }

    public Integer getTwo_tag_id() {
        return two_tag_id;
    }

    public void setTwo_tag_id(Integer two_tag_id) {
        this.two_tag_id = two_tag_id;
    }
}

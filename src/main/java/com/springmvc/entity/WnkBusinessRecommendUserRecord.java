package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/29 04:23
 * @Description:商家推荐用户记录器实体类-用于记录万能卡商家推荐用户记录(记录商家推荐了多少个，每条记录满3状态变更为已兑换，依次循环)
 */
public class WnkBusinessRecommendUserRecord {
    private Integer id;
    private Integer business_id;
    private Integer number;
    private Integer state;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}

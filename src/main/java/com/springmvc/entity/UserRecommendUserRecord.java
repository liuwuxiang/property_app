package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/11/3 17:12
 * @Description:用户推荐用户记录器实体类-用于记录用户推荐用户记录(记录商家推荐了多少个开卡，每条记录满3状态变更为已兑换，依次循环)
 */
public class UserRecommendUserRecord {
    private Integer id;
    private Integer user_id;
    private Integer number;
    private Integer state;

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

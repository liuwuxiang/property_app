package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/12/16 22:00
 * @Description:用户推荐人变更记录表实体类
 */
public class UserRecommendChange {
    private Integer id;
    private Integer before_type;
    private Integer before_recommend_user_id;
    private Integer after_type;
    private Integer after_recommend_user_id;
    private Date change_date;
    private Integer user_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBefore_type() {
        return before_type;
    }

    public void setBefore_type(Integer before_type) {
        this.before_type = before_type;
    }

    public Integer getBefore_recommend_user_id() {
        return before_recommend_user_id;
    }

    public void setBefore_recommend_user_id(Integer before_recommend_user_id) {
        this.before_recommend_user_id = before_recommend_user_id;
    }

    public Integer getAfter_type() {
        return after_type;
    }

    public void setAfter_type(Integer after_type) {
        this.after_type = after_type;
    }

    public Integer getAfter_recommend_user_id() {
        return after_recommend_user_id;
    }

    public void setAfter_recommend_user_id(Integer after_recommend_user_id) {
        this.after_recommend_user_id = after_recommend_user_id;
    }

    public Date getChange_date() {
        return change_date;
    }

    public void setChange_date(Date change_date) {
        this.change_date = change_date;
    }
}

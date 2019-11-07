package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/12/17 16:47
 * @Description:用户搜索历史记录实体类
 */
public class UserSearchHistory {
    private Integer id;
    private Integer user_id;
    private String search_content;

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

    public String getSearch_content() {
        return search_content;
    }

    public void setSearch_content(String search_content) {
        this.search_content = search_content;
    }
}

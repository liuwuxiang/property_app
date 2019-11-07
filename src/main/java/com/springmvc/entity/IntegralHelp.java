package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/11/14 01:31
 * @Description:积分帮助说明实体类(包含用户端平台积分、用户端商家积分、用户端赠送积分、商家端等级积分、商家端推广积分说明数据)
 */
public class IntegralHelp {
    private Integer id;
    private String content;
    private Integer type;
    private Integer open_type;

    public Integer getOpen_type() {
        return open_type;
    }

    public void setOpen_type(Integer open_type) {
        this.open_type = open_type;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

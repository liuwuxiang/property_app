package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/11/8 16:41
 * @Description:万能卡商家系统消息实体类
 */
public class WnkBusinessSystemMessage {
    private Integer id;
    private String title;
    private String content;
    private Integer business_id;
    private Date send_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Date getSend_date() {
        return send_date;
    }

    public void setSend_date(Date send_date) {
        this.send_date = send_date;
    }
}

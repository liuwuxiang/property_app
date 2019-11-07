package com.springmvc.entity;

import java.util.Date;

public class SystemNoticeMessage{
    private Integer id;
    private String title;
    private String content;
    private Integer receiving_object;
    private Date send_time;
    private Integer read_status;

    public Integer getRead_status() {
        return read_status;
    }

    public void setRead_status(Integer read_status) {
        this.read_status = read_status;
    }

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

    public Integer getReceiving_object() {
        return receiving_object;
    }

    public void setReceiving_object(Integer receiving_object) {
        this.receiving_object = receiving_object;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }
}

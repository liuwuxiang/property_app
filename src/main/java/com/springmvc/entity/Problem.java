package com.springmvc.entity;

import java.sql.Timestamp;

/**
 * @author 杨新杰
 * @Date 2018/10/26 10:29
 */
public class Problem {
    private Integer   id;
    private String    title;
    private String    content;
    private Integer   check;
    private Timestamp creation_time;
    private Integer type;
    private Integer open_way;
    private Integer is_del;

    public Integer getIs_del() {
        return is_del;
    }

    public void setIs_del(Integer is_del) {
        this.is_del = is_del;
    }

    public Integer getOpen_way() {
        return open_way;
    }

    public void setOpen_way(Integer open_way) {
        this.open_way = open_way;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Timestamp getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Timestamp creation_time) {
        this.creation_time = creation_time;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", check=" + check +
                ", creation_time=" + creation_time +
                ", type=" + type +
                ", open_way=" + open_way +
                ", is_del=" + is_del +
                '}';
    }
}

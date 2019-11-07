package com.springmvc.entity;

import java.util.Date;

/**
 * @author: zhangfan
 * @Date: 2018/11/20 21:33
 * @Description:万能卡app版本更新记录实体类
 */
public class WnkAppUpdateVersion {
    private Integer id;
    private String version;
    private String file_name;
    private Integer type;
    private Integer is_new_version;
    private Date create_date;
    private Integer update_type;

    public Integer getUpdate_type() {
        return update_type;
    }

    public void setUpdate_type(Integer update_type) {
        this.update_type = update_type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIs_new_version() {
        return is_new_version;
    }

    public void setIs_new_version(Integer is_new_version) {
        this.is_new_version = is_new_version;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }
}

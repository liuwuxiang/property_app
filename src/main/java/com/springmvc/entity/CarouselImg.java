package com.springmvc.entity;

/**
 * 功能描述:
 *
 * @author 杨新杰
 * @date 2018/12/10 14:54
 */
public class CarouselImg {
    private int     id;
    private String  img_url;
    private String  url;
    private Integer position;
    private Integer is_checked;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(Integer is_checked) {
        this.is_checked = is_checked;
    }
}

package com.springmvc.entity;


/**
 * 推广活动实体类
 * @author 杨新杰
 * @Date 2018/11/2 15:03
 */
public class DoingsSpread {
    private Integer id;
    private Integer ad_type;
    private Integer gallery_type;
    private String  gallery_img;
    private String  gallery_content_img;
    private String  system_msg;
    private Integer verify_statue;
    private Integer activating_statue;
    private Integer business_id;
    private String  title;
    private String receive_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAd_type() {
        return ad_type;
    }

    public void setAd_type(Integer ad_type) {
        this.ad_type = ad_type;
    }

    public Integer getGallery_type() {
        return gallery_type;
    }

    public void setGallery_type(Integer gallery_type) {
        this.gallery_type = gallery_type;
    }

    public String getGallery_img() {
        return gallery_img;
    }

    public void setGallery_img(String gallery_img) {
        this.gallery_img = gallery_img;
    }

    public String getGallery_content_img() {
        return gallery_content_img;
    }

    public void setGallery_content_img(String gallery_content_img) {
        this.gallery_content_img = gallery_content_img;
    }

    public String getSystem_msg() {
        return system_msg;
    }

    public void setSystem_msg(String system_msg) {
        this.system_msg = system_msg;
    }

    public Integer getVerify_statue() {
        return verify_statue;
    }

    public void setVerify_statue(Integer verify_statue) {
        this.verify_statue = verify_statue;
    }

    public Integer getActivating_statue() {
        return activating_statue;
    }

    public void setActivating_statue(Integer activating_statue) {
        this.activating_statue = activating_statue;
    }

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceive_type() {
        return receive_type;
    }

    public void setReceive_type(String receive_type) {
        this.receive_type = receive_type;
    }

    @Override
    public String toString() {
        return "DoingsSpread{" +
                "id=" + id +
                ", ad_type=" + ad_type +
                ", gallery_type=" + gallery_type +
                ", gallery_img='" + gallery_img + '\'' +
                ", gallery_contentImg='" + gallery_content_img + '\'' +
                ", system_msg='" + system_msg + '\'' +
                ", verify_statue=" + verify_statue +
                ", activating_statue=" + activating_statue +
                ", business_id=" + business_id +
                ", title='" + title + '\'' +
                '}';
    }
}

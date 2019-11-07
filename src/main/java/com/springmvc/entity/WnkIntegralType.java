package com.springmvc.entity;

/**
 * 商家积分商城 - 商品分类实体类
 * @author 杨新杰
 * @Date 2018/10/10 15:50
 */
public class WnkIntegralType {
    private Integer id;
    private String  name;
    private String  img;
    private String  is_checked;
    private Integer business_id;

    public Integer getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(Integer business_id) {
        this.business_id = business_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getIs_checked() {
        return is_checked;
    }

    public void setIs_checked(String is_checked) {
        this.is_checked = is_checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WnkIntegralType that = (WnkIntegralType) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (img != null ? !img.equals(that.img) : that.img != null) return false;
        if (is_checked != null ? !is_checked.equals(that.is_checked) : that.is_checked != null) return false;
        if (business_id != null ? !business_id.equals(that.business_id) : that.business_id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (is_checked != null ? is_checked.hashCode() : 0);
        result = 31 * result + (business_id != null ? business_id.hashCode() : 0);
        return result;
    }
}

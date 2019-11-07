package com.springmvc.entity;

/**
 * @author: zhangfan
 * @Date: 2018/10/27 19:26
 * @Description:
 */
public class WnkBusinessLevel {
    private Integer id;
    private String level_name;
    private Double price;
    private Integer term_validity;
    private Double obtain_integral;
    private Integer is_default_level;
    private Integer lebel_max;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTerm_validity() {
        return term_validity;
    }

    public void setTerm_validity(Integer term_validity) {
        this.term_validity = term_validity;
    }

    public Double getObtain_integral() {
        return obtain_integral;
    }

    public void setObtain_integral(Double obtain_integral) {
        this.obtain_integral = obtain_integral;
    }

    public Integer getIs_default_level() {
        return is_default_level;
    }

    public void setIs_default_level(Integer is_default_level) {
        this.is_default_level = is_default_level;
    }

    public Integer getLebel_max() {
        return lebel_max;
    }

    public void setLebel_max(Integer lebel_max) {
        this.lebel_max = lebel_max;
    }
}

package com.springmvc.entity;

public class ResidentialUnit {

    private Integer id;
    private Integer residential_building_id;
    private String unit_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResidential_building_id() {
        return residential_building_id;
    }

    public void setResidential_building_id(Integer residential_building_id) {
        this.residential_building_id = residential_building_id;
    }

    public String getUnit_number() {
        return unit_number;
    }

    public void setUnit_number(String unit_number) {
        this.unit_number = unit_number;
    }
}

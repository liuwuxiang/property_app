package com.springmvc.entity;

public class ResidentialHousesNumber {
    private Integer id;
    private Integer residential_id;
    private Integer residential_building_id;
    private Integer residential_unit_id;
    private String house_number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResidential_id() {
        return residential_id;
    }

    public void setResidential_id(Integer residential_id) {
        this.residential_id = residential_id;
    }

    public Integer getResidential_building_id() {
        return residential_building_id;
    }

    public void setResidential_building_id(Integer residential_building_id) {
        this.residential_building_id = residential_building_id;
    }

    public Integer getResidential_unit_id() {
        return residential_unit_id;
    }

    public void setResidential_unit_id(Integer residential_unit_id) {
        this.residential_unit_id = residential_unit_id;
    }

    public String getHouse_number() {
        return house_number;
    }

    public void setHouse_number(String house_number) {
        this.house_number = house_number;
    }
}

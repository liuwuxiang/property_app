package com.springmvc.entity;

public class ResidentialBuilding {
    private Integer id;
    private Integer residential_id;
    private String building_number;

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

    public String getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(String building_number) {
        this.building_number = building_number;
    }
}

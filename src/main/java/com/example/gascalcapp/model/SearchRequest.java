package com.example.gascalcapp.model;

public class SearchRequest {
       private int region;
    private String province;
    private String town;

    // Constructors, getters, and setters

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}

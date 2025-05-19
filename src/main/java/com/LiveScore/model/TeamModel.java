package com.LiveScore.model;

public class TeamModel {
    private String name;
    private String city;
    private String logoUrl;

    public TeamModel() {
    }

    public TeamModel(String name) {
        this.name = name;
    }

    public TeamModel(String name, String city, String logoUrl) {
        this.name = name;
        this.city = city;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}

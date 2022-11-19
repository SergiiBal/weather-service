package com.sergii.services;

public class WeatherDetails {
    protected String lat;
    protected String lng;
    protected String address;

    public WeatherDetails(String lat, String lng, String address) {
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setSex(String sex) {
        this.address = address;
    }
}

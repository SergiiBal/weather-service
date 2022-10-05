package com.sergii.models;

public record CityResponse(
        Integer geonameid,
        String name,
        Integer population,
        Double latitude,
        Double longitude,
        Division division
) {
}

record Division(String code, Integer geonameid, String name, String type) {
}
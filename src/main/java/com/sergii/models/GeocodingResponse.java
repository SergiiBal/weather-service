package com.sergii.models;


import java.util.List;

public record GeocodingResponse(List<Result> results)
{
}
record Result(Geometry geometry) {
}

record Geometry(Location location) {
}

record Location(Double lat, Double lng) {
}
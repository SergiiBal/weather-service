package com.sergii.models;

import java.util.Map;

public record CountriesResponse(Map<String, String> countries, String status) {
}

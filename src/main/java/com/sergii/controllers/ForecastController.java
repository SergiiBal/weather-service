package com.sergii.controllers;

import com.sergii.services.ForecastService;
import com.sergii.services.GeocodingService;
import com.sergii.services.WeatherDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cities")
public class ForecastController {
    private final RestTemplate restTemplate;
    private final ForecastService forecastService;
    private final GeocodingService geocodingService;


    @Autowired
    public ForecastController(RestTemplate restTemplate, ForecastService forecastService, GeocodingService geocodingService) {
        this.restTemplate = restTemplate;
        this.forecastService = forecastService;
        this.geocodingService = geocodingService;
    }

    // http://localhost:8080/cities/forecast?address=164%20Townsend%20St.,%20San%20Francisco,%20CA&date=2022-11-17
    // http://localhost:8080/cities/forecast?address=164%20Townsend%20St.,%20San%20Francisco,%20CA
    @Cacheable("Forecast")
    @GetMapping("/forecast")
    public String forecast(@RequestParam String address, @RequestParam(required = false) String date) {
        WeatherDetails details = geocodingService.getLocation(address);
        String lat = details.getLat();
        String lng = details.getLng();
        String location = details.getAddress();
               if (date == null || date.isEmpty()) {
            return forecastService.getWeatherNow(lat, lng, location);
        } else {
            return forecastService.getWeather(lat, lng, date, location);
        }
    }
}



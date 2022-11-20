package com.sergii.controllers;

import com.sergii.models.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final RestTemplate restTemplate;

    @Autowired
    public CityController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("CityDetails")
    @GetMapping("/cityDetails")
    public CityResponse cityDetails(@RequestParam Integer geonameid) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://countries-cities.p.rapidapi.com/location/city/{geonameid}", geonameid)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "countries-cities.p.rapidapi.com")
                .build();
        ResponseEntity<CityResponse> responseEntity = restTemplate.exchange(
                requestEntity,
                CityResponse.class);
        return responseEntity.getBody();
    }

    @Cacheable("Cities")
    @GetMapping("/total/{countryCode}")
    public Integer totalCities(@PathVariable String countryCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "countries-cities.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        System.out.println("Making Rest Call");

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                "https://countries-cities.p.rapidapi.com/location/country/{countryCode}/city/list",
                HttpMethod.GET,
                requestEntity,
                Map.class,
                countryCode);
        Map body = responseEntity.getBody();
        return (Integer) body.get("total_cities");
    }
}


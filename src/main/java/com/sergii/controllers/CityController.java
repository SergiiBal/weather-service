package com.sergii.controllers;

import com.sergii.models.CitiesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/cities")
public class CityController {
    private final RestTemplate restTemplate;

    @Autowired
    public CityController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("CityList")
    @GetMapping("/geo")
    public String listCities(@RequestParam(name = "key", defaultValue = "") Integer geonameid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "countries-cities.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        System.out.println("Making Rest Call");

        ResponseEntity<CitiesResponse> responseEntity = restTemplate.exchange(
                "https://countries-cities.p.rapidapi.com/location/city/5128580",
                HttpMethod.GET,
                requestEntity,
                CitiesResponse.class);
        return responseEntity.getBody().cities().get(geonameid);

    }
}


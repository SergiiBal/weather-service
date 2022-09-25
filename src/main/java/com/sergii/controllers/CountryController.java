package com.sergii.controllers;

import com.sergii.models.CountriesResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/country")
public class CountryController {
    private final RestTemplate restTemplate;

    @Autowired
    public CountryController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/list")
    public String listCountries(@RequestParam(name = "key", defaultValue = "") String key) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "countries-cities.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CountriesResponse> responseEntity = restTemplate.exchange(
                "https://countries-cities.p.rapidapi.com/location/country/list",
                HttpMethod.GET,
                requestEntity,
                CountriesResponse.class);
        if (!key.isEmpty()) {
            return responseEntity.getBody().countries().get(key);
        } else {
            return responseEntity.getBody().countries().toString();
        }
    }
}

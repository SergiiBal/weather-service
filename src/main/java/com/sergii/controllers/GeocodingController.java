package com.sergii.controllers;

import com.sergii.models.GeocodingResponse;
import com.sergii.services.GeocodingService;
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
public class GeocodingController {
    private final RestTemplate restTemplate;
    private final GeocodingService geocodingService;


    @Autowired
    public GeocodingController(RestTemplate restTemplate, GeocodingService geocodingService) {
        this.restTemplate = restTemplate;
        this.geocodingService = geocodingService;
    }

    @Cacheable("Address")
    @GetMapping("/address")
    public String geocodingDetails(@RequestParam String address) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address={address}", address)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }
// http://localhost:8080/cities/address2?address=164%20Townsend%20St.,%20San%20Francisco,%20CA
    @Cacheable("Address2")
    @GetMapping("/address2")
    public String geocodingDetails2(@RequestParam String address) {
        return geocodingService.getLocation(address);
    }

    @Cacheable("Address3")
    @GetMapping("/address3")
    public String geocodingDetails3(@RequestParam String address) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address={address}", address)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
                .build();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                requestEntity,
                Map.class);
        // results[0]/geometry/location/lat,lng
        Map map = responseEntity.getBody();
        List results = (List) map.get("results");
        Map result = (Map) results.get(0);
        Map geometry = (Map) result.get("geometry");
        Map location = (Map) geometry.get("location");
        Double lat = (Double) location.get("lat");
        Double lng = (Double) location.get("lng");
        return lat + ":" + lng;
    }
}



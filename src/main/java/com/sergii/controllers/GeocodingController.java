package com.sergii.controllers;

import com.sergii.models.GeocodingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/cities")
public class GeocodingController {
    private final RestTemplate restTemplate;

    @Autowired
    public GeocodingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("Address")
    @GetMapping("/address")
    public GeocodingResponse geocodingDetails(@RequestParam String address) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://google-maps-geocoding.p.rapidapi.com/geocode/{address}", address)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
                .build();

        System.out.println("Making Rest Call");

        ResponseEntity<GeocodingResponse> responseEntity = restTemplate.exchange(
                requestEntity,
                GeocodingResponse.class);
        return responseEntity.getBody();
    }

    @Cacheable("Address2")
    @GetMapping("/adress2/{address}")
    public String geocodingDetails2(@PathVariable String address) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        System.out.println("Making Rest Call");

        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                "https://google-maps-geocoding.p.rapidapi.com/geocode/",
                HttpMethod.GET,
                requestEntity,
                Map.class,
                address);
       Map body = responseEntity.getBody();
       return (String) body.get("latitude");
    //    return (String) responseEntity.getBody().get("latlng");
    }
}



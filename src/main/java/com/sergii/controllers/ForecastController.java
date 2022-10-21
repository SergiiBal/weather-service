package com.sergii.controllers;

import com.sergii.models.ForecastResponse;
import com.sergii.models.GeocodingResponse;
import com.sergii.services.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cities")
public class ForecastController {
    private final RestTemplate restTemplate;
    private final ForecastService forecastService;


    @Autowired
    public ForecastController(RestTemplate restTemplate, ForecastService forecastService) {
        this.restTemplate = restTemplate;
        this.forecastService = forecastService;
    }

    @Cacheable("Forecast")
  //  @GetMapping("/forecast/{lat},{lng},{time}")
 /*   public String forecast(@PathVariable String lat, String lng, String time) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "dark-sky.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        System.out.println("Making Rest Call");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://dark-sky.p.rapidapi.com/{lat},{lng},{time}",
                HttpMethod.GET,
                requestEntity,
                String.class,
                lat,
                lng,
                time);
        return responseEntity.getBody();
    }*/
    @GetMapping("/forecast/{latlngtime}")
    public String forecast(@PathVariable String latlngtime) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "dark-sky.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        System.out.println("Making Rest Call");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://dark-sky.p.rapidapi.com/{latlngtime}",
                HttpMethod.GET,
                requestEntity,
                String.class,
                latlngtime);
        return responseEntity.getBody();
    }

    @Cacheable("Forecast2")
    @GetMapping("/forecast2")
    public String forecast2(@RequestParam String lat, String lng, String time) {
        return forecastService.getWeather(lat, lng, time);
    }

    @Cacheable("Forecast3")
    @GetMapping("/forecast3")
    public String forecast3(@RequestParam String lat, String lng, String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/json?lat={lat}&lng={lng}&time={time}", lat, lng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }
//https://dark-sky.p.rapidapi.com/30,40?units=auto&lang=en
    // http://localhost:8080/cities/forecast4/37.774929,-122.419418?units=auto&lang=en
    @Cacheable("Forecast4")
    @GetMapping("/forecast4")
    public String forecast4(@RequestParam String latlng, String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{latlng}?units=auto&time={time}", latlng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }

    @Cacheable("Forecast5")
    @GetMapping("/forecast5")
    public String forecast5(@RequestParam String lat, String lng, String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{lat},{lng}?units=auto&time={time}", lat, lng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }


    }
    /*@Cacheable("Address2")
    @GetMapping("/address2")
    public GeocodingResponse geocodingDetails2(@RequestParam String address) {
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
        Map geometry = (Map)result.get("geometry");
        Map location = (Map)geometry.get("location");
        Double lat = (Double) location.get("lat");
        Double lng = (Double) location.get("lng");
        return lat + ":" + lng;
    }*/




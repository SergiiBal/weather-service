package com.sergii.services;

import com.sergii.models.ForecastResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ForecastService {
    private final RestTemplate restTemplate;

    @Autowired
    public ForecastService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeather (String lat, String lng, String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{lat},{lng},{time}", lat, lng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }

}

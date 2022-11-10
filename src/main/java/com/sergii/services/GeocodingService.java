package com.sergii.services;

import com.sergii.models.GeocodingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodingService {
    private final RestTemplate restTemplate;

    @Autowired
    public GeocodingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLocation (String address) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address={address}", address)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "google-maps-geocoding.p.rapidapi.com")
                .build();
        ResponseEntity<GeocodingResponse> responseEntity = restTemplate.exchange(
                requestEntity,
                GeocodingResponse.class);
        // results[0]/geometry/location/lat,lng
        Double lat = (Double) responseEntity.getBody().results().get(0).geometry().location().lat();
        Double lng = (Double) responseEntity.getBody().results().get(0).geometry().location().lng();
        return lat + "," + lng + "&" + address;
    }
 //   latlng = getLocation(address);
//    getWeather( latlng, time)

}

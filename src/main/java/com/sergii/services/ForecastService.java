package com.sergii.services;

import com.sergii.models.weather.CurrentWeatherRoot;
import com.sergii.models.weather.HistoricalWeatherRoot;
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

    public String getWeather (String lat, String lng, String date, String location) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://weatherapi-com.p.rapidapi.com/history.json?q={lat},{lng}&dt={date}", lat, lng, date)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();
        ResponseEntity<HistoricalWeatherRoot> responseEntity = restTemplate.exchange(
                requestEntity,
                HistoricalWeatherRoot.class);
        HistoricalWeatherRoot body = responseEntity.getBody();
        assert body != null;
        String resultWeather = body.forecast.forecastday.get(0).day.condition.text;
        String minTemperature = String.valueOf(body.forecast.forecastday.get(0).day.mintemp_c);
        String maxTemperature = String.valueOf(body.forecast.forecastday.get(0).day.maxtemp_c);
        return "It was " + resultWeather + " in " + location + ". Minimum temperature that day was near " + minTemperature + " degrees, maximum near " + maxTemperature +" degrees.";
    }

    public String getWeatherNow (String lat, String lng, String location) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://weatherapi-com.p.rapidapi.com/current.json?q={lat},{lng}", lat, lng)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .build();
        ResponseEntity<CurrentWeatherRoot> responseEntity = restTemplate.exchange(
                requestEntity,
                CurrentWeatherRoot.class);
        return "It is " + responseEntity.getBody().current.condition.text + " in " + location + ". The temperature at the moment is near " + responseEntity.getBody().current.temp_c + " degrees.";
    }


}

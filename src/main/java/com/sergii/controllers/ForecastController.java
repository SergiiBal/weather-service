package com.sergii.controllers;

import com.sergii.services.ForecastService;
import com.sergii.services.GeocodingService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    //http://localhost:8080/cities/forecast/37.774929,-122.419418
    //http://localhost:8080/cities/forecast/37.774929,-122.419418&2019-02-20
    //http://localhost:8080/cities/forecast/37.774929,-122.419418/2019-02-20
    @GetMapping("/forecast/{latlng}/{time}")
    public String forecast(@PathVariable String latlng, @PathVariable String time) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb");
        headers.set("X-RapidAPI-Host", "dark-sky.p.rapidapi.com");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        System.out.println("Making Rest Call");

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://dark-sky.p.rapidapi.com/{latlng}?units=auto&time={time}",
                HttpMethod.GET,
                requestEntity,
                String.class,
                latlng,
                time);
        return responseEntity.getBody();
    }

    @Cacheable("Forecast2")
    @GetMapping("/forecast2")
    public String forecast2(@RequestParam String lat, @RequestParam String lng, @RequestParam(required = false) String time, String location) {
        if (time == null || time.isEmpty()) {
            return forecastService.getWeatherNow(lat, lng, location);
        } else {
            return forecastService.getWeather(lat, lng, time, location);
        }
    }

    // http://localhost:8080/cities/forecast20?address=164%20Townsend%20St.,%20San%20Francisco,%20CA&date=2022-10-26
    // http://localhost:8080/cities/forecast20?address=164%20Townsend%20St.,%20San%20Francisco,%20CA
    @Cacheable("Forecast20")
    @GetMapping("/forecast20")
    public String forecast20(@RequestParam String address, @RequestParam(required = false) String date) {
        String latlng = geocodingService.getLocation(address);
        String[] coordinates = latlng.split(",");
        String[] givenLocation = latlng.split("&");
        String lat = coordinates[0];
        String lng = coordinates[1];
        String location = givenLocation[1];
        if (date == null || date.isEmpty()) {
            return forecastService.getWeatherNow(lat, lng, location);
        } else {
            return forecastService.getWeather(lat, lng, date, location);
        }
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
    //http://localhost:8080/cities/forecast4?latlng=37.774929,-122.419418&time=0
    //http://localhost:8080/cities/forecast4?latlng=37.774929,-122.419418&time=2019-02-20
    @Cacheable("Forecast4")
    @GetMapping("/forecast4")
    public String forecast4(@RequestParam String latlng, @RequestParam String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{latlng}?units=auto&time={time}", latlng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }

    //http://localhost:8080/cities/forecast5?lat=37.774929&lng-122.419418&time=0
    //
    @Cacheable("Forecast5")
    @GetMapping("/forecast5")
    public String forecast5(@RequestParam String lat, @RequestParam String lng, @RequestParam String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{lat,lng}?units=auto&time={time}", lat, lng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                requestEntity,
                String.class);
        return responseEntity.getBody();
    }

    //http://localhost:8080/cities/forecast6?lat=37.774929&lng=-122.419418&time=2019-02-20
    @Cacheable("Forecast6")
    @GetMapping("/forecast6")
    public String forecast6(@RequestParam String lat, @RequestParam String lng, @RequestParam String time) {
        String latLngTime = lat + "," + lng + "," + time;
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{latLngTime}", latLngTime)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                requestEntity,
                Map.class);
        Map map = responseEntity.getBody();
        if (time.isEmpty() || time.equals("0")) {
            Map timeUndefinite = (Map) map.get("currently");
            String weatherAtTheMoment = (String) timeUndefinite.get("summary");
            return "It is " + weatherAtTheMoment + " in Kyiv";
        } else {
            Map timeSpecified = (Map) map.get("daily");
            List weatherAtTheTime = (List) timeSpecified.get("data");
            Map result = (Map) weatherAtTheTime.get(0);
            String resultWeather = (String) result.get("summary");
            return "It is not 0 or empty" + resultWeather;
        }
    }

    //http://localhost:8080/cities/forecast7?latlng=37.774929,-122.419418&time=2019-02-20
    @Cacheable("Forecast7")
    @GetMapping("/forecast7")
    public String forecast7(@RequestParam String latlng, @RequestParam String time) {
        RequestEntity<Void> requestEntity = RequestEntity.get("https://dark-sky.p.rapidapi.com/{latlng}?units=auto&time={time}", latlng, time)
                .header("X-RapidAPI-Key", "0a83e848e8mshe0477d46cde4ac7p180993jsn813f039b57fb")
                .header("X-RapidAPI-Host", "dark-sky.p.rapidapi.com")
                .build();
        ResponseEntity<Map> responseEntity = restTemplate.exchange(
                requestEntity,
                Map.class);
        Map map = responseEntity.getBody();
        if (time.isEmpty() || time.equals("0")) {
            Map timeUndefinite = (Map) map.get("currently");
            String weatherAtTheMoment = (String) timeUndefinite.get("summary");
            return "It is " + weatherAtTheMoment + " in Kyiv";
        } else {
            Map timeSpecified = (Map) map.get("daily");
            List weatherAtTheTime = (List) timeSpecified.get("data");
            String weatherAtTheTimeS = weatherAtTheTime.toString();
            Map result = (Map) weatherAtTheTime.get(0);
            String resultWeather = (String) result.get("summary");
            Integer resultTime = (Integer) result.get("time");
            String timeGetWeather = "1666940400";
            //     List<Map> weathe = JsonPath.from(weatherAtTheTimeS).get("data.time.findAll { time -> data.time = 1666335600}");
            //  System.out.println(getWeatherForGivenTime(weatherAtTheTimeS,timeGetWeather));
           /* String resultTime2 = String.valueOf(resultTime);
            long epoch = System.currentTimeMillis();
            long epoch = Long.parseLong(resultTime2);
            Date expiry = new Date(epoch * 1000);
            System.out.println(expiry);*/
            //   String a = convertStringToTimestamp(time).toString();
            //   String b = DateFormat.getDateInstance(DateFormat.SHORT).format(1666767600000L).toString();

            //   String date = (String)  result.get("time");
//            String text = "2019-02-20";
//            LocalDateTime dateTime = LocalDate.parse(text).atStartOfDay();
//            System.out.println(dateTime);
            return "It is not 0 or empty" + resultWeather + "ffffff";
        }
    }

    public List<String> getWeatherForGivenTime(String weatherAtTheTime, String timeGetWeather) {
        JSONArray jsonArray = new JSONArray(weatherAtTheTime);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> ((JSONObject) jsonArray.get(index)).optString(timeGetWeather))
                .collect(Collectors.toList());
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




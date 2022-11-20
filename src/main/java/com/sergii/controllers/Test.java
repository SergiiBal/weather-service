package com.sergii.controllers;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Test {
    public static void main(String[] args) {
        String timeGetWeather = "1666940400";
        String timeRequest = "2019-02-20";
        String timeZone = "America/Los_Angeles";
        long epoch = Long.parseLong(timeGetWeather);
        Instant instant = Instant.ofEpochSecond(epoch);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, ZoneId.of(timeZone));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.toLocalDate().toString());
        System.out.println(zonedDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}

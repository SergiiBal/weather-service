package com.sergii.services;

import org.springframework.stereotype.Service;

@Service
public class LikeService {
    public String whoLikesIt(String... names) {
        String resultString;
        switch (names.length) {
            case 0 -> resultString = "no one likes this";
            case 1 -> resultString = names[0] + " likes this";
            case 2 -> resultString = names[0] + " and " + names[1] + " like this";
            case 3 -> resultString = names[0] + ", " + names[1] + " and " + names[2] + " like this";
            default -> resultString = names[0] + ", " + names[1] + " and " + (names.length - 2) + " others like this";
        }
        System.out.println(resultString);
        return resultString;
    }
}

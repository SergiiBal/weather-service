package com.sergii.services;

import org.springframework.stereotype.Service;

@Service
public class LikeService {
    public String whoLikesIt(String... names) {
        String[] inputNames = names;
        String resultString = "";
        if (inputNames.length == 0) {
            resultString = "no one likes this";
            System.out.println(resultString);
        } else if (inputNames.length == 1) {
            for (int i = 0; i < inputNames.length; i++) {
                resultString = (resultString + inputNames[i] + " likes this");

            }
            System.out.println(resultString);
        } else if (inputNames.length == 2) {
            for (int i = 0; i < inputNames.length; i++) {
                resultString = (inputNames[0] + " and " + inputNames[1]);
            }
            resultString = (resultString + " like this");
            System.out.println(resultString);
        } else if (inputNames.length == 3) {
            for (int i = 0; i < inputNames.length; i++) {
                resultString = (inputNames[0] + ", " + inputNames[1] + " and " + inputNames[2]);
            }
            resultString = (resultString + " like this");
            System.out.println(resultString);
        } else if (inputNames.length > 3) {
            for (int i = 0; i < inputNames.length; i++) {
                resultString = (inputNames[0] + ", " + inputNames[1] + " and " + (inputNames.length-2) + " others like this");
            }
            System.out.println(resultString);
        }
        return resultString;
    }
}
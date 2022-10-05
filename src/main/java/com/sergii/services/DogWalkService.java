package com.sergii.services;


import org.springframework.stereotype.Service;

@Service
public class DogWalkService implements WalkAnimalService {
    @Override
    public String walk() {
        return "Walking with Dog";
    }
}



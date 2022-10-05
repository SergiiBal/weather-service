package com.sergii.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class CatWalkService implements WalkAnimalService {
    @Override
    public String walk() {
        return "Walking with Cat";
    }
}



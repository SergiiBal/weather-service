package com.sergii.controllers;

import com.sergii.services.WalkAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {
    private final WalkAnimalService walkAnimalService;

    @Autowired
    public AnimalController(WalkAnimalService walkAnimalService) {
        this.walkAnimalService = walkAnimalService;
    }

    @GetMapping("/walk")
    public String Walk() {
        return walkAnimalService.walk();
    }
}


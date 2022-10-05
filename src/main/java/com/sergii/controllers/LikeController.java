package com.sergii.controllers;


import com.sergii.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/like")
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @Cacheable("LikeList")
    @GetMapping("/names")
    public String listNames(@RequestParam(name = "key", defaultValue = "") String key) {
        System.out.println("Typing names..");
        return likeService.whoLikesIt(key);
    }
}


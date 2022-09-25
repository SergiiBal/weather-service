package com.sergii.controllers;

import com.sergii.services.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkerController {
    private final WorkService workService;

    @Autowired
    public WorkerController(WorkService workService) {
        this.workService = workService;
    }

    @GetMapping("/work")
    public String Work() {
        return workService.work();
    }
}

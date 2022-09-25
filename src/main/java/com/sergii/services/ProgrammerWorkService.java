package com.sergii.services;

import org.springframework.stereotype.Service;

@Service
public class ProgrammerWorkService implements WorkService {
    @Override
    public String work() {
        return "Programming";
    }
}

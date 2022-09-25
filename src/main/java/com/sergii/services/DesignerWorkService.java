package com.sergii.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class DesignerWorkService implements WorkService {
    @Override
    public String work() {
        return "Designing";
    }
}

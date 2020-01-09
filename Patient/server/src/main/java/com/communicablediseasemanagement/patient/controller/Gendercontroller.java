package com.communicablediseasemanagement.riskarea.controller;

import com.communicablediseasemanagement.riskarea.entity.Gender;
import com.communicablediseasemanagement.riskarea.repository.Genderrepository;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Gendercontroller {

    @Autowired
    private final Genderrepository genderRepository;

    public Gendercontroller(Genderrepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @GetMapping("/gender")
    public Collection<Gender> genders() {
        return genderRepository.findAll().stream().collect(Collectors.toList());
    }

}
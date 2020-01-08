package com.communicablediseasemanagement.riskarea.controller;

import com.communicablediseasemanagement.riskarea.entity.Disease;
import com.communicablediseasemanagement.riskarea.repository.Diseaserepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Diseasecontroller {

    @Autowired
    private final Diseaserepository diseaserepository;

    public Diseasecontroller(Diseaserepository diseaserepository) {
        this.diseaserepository = diseaserepository;
    }

    @GetMapping("/disease")
    public Collection<Disease> diseases() {
        return diseaserepository.findAll().stream().collect(Collectors.toList());
    }

}
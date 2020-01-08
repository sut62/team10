package com.communicablediseasemanagement.patient.controller;

import com.communicablediseasemanagement.patient.entity.Bloodtype;
import com.communicablediseasemanagement.patient.repository.Bloodtyperepository;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Bloodtypecontroller {

    @Autowired
    private final Bloodtyperepository bloodtyperepository;

    public Bloodtypecontroller(Bloodtyperepository bloodtyperepository) {
        this.bloodtyperepository = bloodtyperepository;
    }

    @GetMapping("/bloodtype")
    public Collection<Bloodtype> bloodtypes() {
        return bloodtyperepository.findAll().stream().collect(Collectors.toList());
    }

}
package com.sut.se.G10.Patient.Controller ;

import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BloodtypeController {

    @Autowired
    private final BloodtypeRepository bloodtypeRepository;

    public BloodtypeController(BloodtypeRepository bloodtypeRepository) {
        this.bloodtypeRepository = bloodtypeRepository;
    }

    @GetMapping("/bloodtype")
    public Collection<Bloodtype> bloodtypes() {
        return bloodtypeRepository.findAll().stream().collect(Collectors.toList());
    }

}
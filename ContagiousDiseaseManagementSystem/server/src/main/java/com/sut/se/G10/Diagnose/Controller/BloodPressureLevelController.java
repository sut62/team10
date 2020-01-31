package com.sut.se.G10.Diagnose.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;
import com.sut.se.G10.Diagnose.Repository.BloodPressureLevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class BloodPressureLevelController {

    @Autowired 
    private BloodPressureLevelRepository bloodPressureLevelRepository;

    @GetMapping("/bloodPressureLevel")
    public Collection<BloodPressureLevel> getBloodPressureLevels() {
        return bloodPressureLevelRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/bloodPressureLevelByLevel/{level}")
    public BloodPressureLevel getBloodPressureLevelByLevel(@PathVariable String level) {
        return bloodPressureLevelRepository.findByLevel(level);
    }

    @GetMapping("/bloodPressureLevel/{id}")
    public BloodPressureLevel getBloodPressureLevel(@PathVariable long id) {
        return bloodPressureLevelRepository.findById(id);
    }
}
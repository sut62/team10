package com.sut.se.G10.Controller;

import com.sut.se.G10.Entity.Disease;
import com.sut.se.G10.Repository.DiseaseRepository;

import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DiseaseController {

    @Autowired
    private final DiseaseRepository diseaseRepository;

    public DiseaseController(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @GetMapping("/disease")
    public Collection<Disease> disease() {
        return diseaseRepository.findAll().stream().collect(Collectors.toList());
    }
    // @GetMapping("/disease/{id}")
    // public Optional<Disease> Durations(@PathVariable Long id) {
    //     Optional<Disease> disease = diseaseRepository.findById(id);
    //     return disease;
    // }
}
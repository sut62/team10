package com.sut.se.G10.Contagion.Controller;

import com.sut.se.G10.Contagion.Entity.Symptom;
import com.sut.se.G10.Contagion.Repository.SymptomRepository;

import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SymptomController {

    @Autowired
    private final SymptomRepository symptomRepository;

    public SymptomController(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @GetMapping("/symptom")
    public Collection<Symptom> symptoms() {
        return symptomRepository.findAll().stream().collect(Collectors.toList());
    }
}
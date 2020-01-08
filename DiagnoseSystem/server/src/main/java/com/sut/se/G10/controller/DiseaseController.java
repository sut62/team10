package com.sut.se.G10.controller;

import java.util.List;
import java.util.Optional;

import com.sut.se.G10.entity.Disease;
import com.sut.se.G10.repository.DiseaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DiseaseController {

    @Autowired private DiseaseRepository diseaseRepository;

    @GetMapping("/disease")
    public List<Disease> getDiseases() {
        return diseaseRepository.findAll();
    }

    @GetMapping("/disease/{id}")
    public Optional<Disease> getDisease(@PathVariable Long id) {
        return diseaseRepository.findById(id);
    }

    @GetMapping("/disease/{name}")
    public Disease getDisease(@PathVariable String name) {
        return diseaseRepository.findByName(name);
    }
}
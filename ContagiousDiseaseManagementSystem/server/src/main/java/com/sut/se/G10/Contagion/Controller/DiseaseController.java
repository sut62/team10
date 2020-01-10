package com.sut.se.G10.Contagion.Controller;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;

import java.util.Collection;
import java.util.Optional;
// import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PathVariable;
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
    public Collection<Disease> diseases() {
        return diseaseRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/disease/{id}")
    public Optional<Disease> getDisease(@PathVariable Long id) {
        return diseaseRepository.findById(id);
    }
}
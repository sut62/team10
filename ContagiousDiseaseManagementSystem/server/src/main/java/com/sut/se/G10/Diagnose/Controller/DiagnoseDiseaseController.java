package com.sut.se.G10.Diagnose.Controller;

import com.sut.se.G10.Diagnose.Entity.DiagnoseDisease;
import com.sut.se.G10.Diagnose.Repository.DiagnoseDiseaseRepository;
import com.sut.se.G10.Diagnose.Entity.Diagnose;
import com.sut.se.G10.Diagnose.Repository.DiagnoseRepository;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class DiagnoseDiseaseController {

    @Autowired
    private DiagnoseDiseaseRepository diagnoseDiseaseRepository;
    @Autowired
    private DiagnoseRepository diagnoseRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;

    @GetMapping("/diagnoseDisease")
    public List<DiagnoseDisease> getDiagnoseDiseases() {
        return diagnoseDiseaseRepository.findAll();
    }

    @GetMapping("/diagnoseDisease/{id}")
    public Optional<DiagnoseDisease> getDiagnoseDisease(@PathVariable Long id) {
        return diagnoseDiseaseRepository.findById(id);
    }

}
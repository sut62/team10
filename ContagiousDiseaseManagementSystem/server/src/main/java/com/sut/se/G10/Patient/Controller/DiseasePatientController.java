package com.sut.se.G10.Patient.Controller;

import com.sut.se.G10.Patient.Entity.DiseasePatient;
import com.sut.se.G10.Patient.Repository.DiseasePatientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class DiseasePatientController {

    @Autowired
    private DiseasePatientRepository diseasePatientRepository;

    @GetMapping("/diseasepatient")
    public List<DiseasePatient> getDiseasePatient() {
        return diseasePatientRepository.findAll();
    }

    @GetMapping("/diseasepatient/{id}")
    public Optional<DiseasePatient> getDiseasePatient(@PathVariable Long id) {
        return  diseasePatientRepository.findById(id);
    }

}
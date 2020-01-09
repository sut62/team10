package com.sut.se.G10.Diagnose.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.se.G10.Diagnose.Entity.Admission;
import com.sut.se.G10.Diagnose.Repository.AdmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class AdmissionController {

    @Autowired 
    private final AdmissionRepository admissionRepository;

    public AdmissionController(AdmissionRepository admissionRepository) {
        this.admissionRepository = admissionRepository ;
    }

    @GetMapping("/admission")
    public Collection<Admission> getAdmissions() {
        return admissionRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/admission/{admitted}")
    public Admission getAdmission(@PathVariable String admitted) {
        return admissionRepository.findByAdmitted(admitted);
    }
}
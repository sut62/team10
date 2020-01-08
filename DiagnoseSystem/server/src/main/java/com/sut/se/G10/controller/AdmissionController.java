package com.sut.se.G10.controller;

import java.util.List;
import java.util.Optional;

import com.sut.se.G10.entity.Admission;
import com.sut.se.G10.repository.AdmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class AdmissionController {

    @Autowired private AdmissionRepository admissionRepository;

    @GetMapping("/admission")
    public List<Admission> getAdmissions() {
        return admissionRepository.findAll();
    }

    @GetMapping("/admission/{id}")
    public Optional<Admission> getAdmission(@PathVariable Long id) {
        return admissionRepository.findById(id);
    }

    @GetMapping("/admission/{admitted}")
    public Admission getAdmission(@PathVariable String admitted) {
        return admissionRepository.findByAdmitted(admitted);
    }
}
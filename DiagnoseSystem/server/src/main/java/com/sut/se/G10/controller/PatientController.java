package com.sut.se.G10.controller;

import java.util.List;
import java.util.Optional;

import com.sut.se.G10.entity.Patient;
import com.sut.se.G10.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PatientController {

    @Autowired private PatientRepository patientRepository;

    @GetMapping("/patient")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public Optional<Patient> getDisease(@PathVariable Long id) {
        return patientRepository.findById(id);
    }
}
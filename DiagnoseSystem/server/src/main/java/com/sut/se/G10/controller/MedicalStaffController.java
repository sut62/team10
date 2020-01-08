package com.sut.se.G10.controller;

import com.sut.se.G10.entity.MedicalStaff;
import com.sut.se.G10.repository.MedicalStaffRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MedicalStaffController {

    @Autowired private MedicalStaffRepository medicalStaffRepository;

    @GetMapping("/medicalStaff")
    public List<MedicalStaff> getMedicalStaffs() {
        return medicalStaffRepository.findAll();
    }

    @GetMapping("/medicalStaff/{id}")
    public Optional<MedicalStaff> getMedicalStaff(@PathVariable Long id) {
        return medicalStaffRepository.findById(id);
    }
}
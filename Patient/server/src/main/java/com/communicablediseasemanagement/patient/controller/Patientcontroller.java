package com.communicablediseasemanagement.riskarea.controller;

import com.communicablediseasemanagement.riskarea.entity.Patient;
import com.communicablediseasemanagement.riskarea.repository.Patientrepository;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import com.communicablediseasemanagement.riskarea.entity.Gender;
import com.communicablediseasemanagement.riskarea.entity.Bloodtype;
import com.communicablediseasemanagement.riskarea.entity.Disease;
import com.communicablediseasemanagement.riskarea.repository.Genderrepository;
import com.communicablediseasemanagement.riskarea.repository.Bloodtyperepository;
import com.communicablediseasemanagement.riskarea.repository.Diseaserepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@CrossOrigin(origins = {"http://localhost:8080"})
@RestController
public class Patientcontroller {

    @Autowired 
    private final Patientrepository patientrepository;
    @Autowired 
    private Genderrepository genderrepository;
    @Autowired 
    private Bloodtyperepository  bloodtyperepository;
    @Autowired 
    private Diseaserepository diseaserepository;

    Patientcontroller(  Patientrepository patientrepository,
                        Genderrepository genderrepository,
                        Bloodtyperepository  bloodtyperepository,
                        Diseaserepository diseaserepository) {
        this.patientrepository = patientrepository;
        this.genderrepository = genderrepository;
        this.bloodtyperepository = bloodtyperepository;
        this.diseaserepository = diseaserepository;
    }
    
    @GetMapping("/patient")
    public Collection<Patient> patients() {
        return patientrepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/patient/{fullname}/{gender_id}/{bdate}/{bloodtype_id}/{phone}/{address}/{disease_id}")
    public Patient newPatient(
                        @PathVariable String fullname, 
                        @PathVariable long gender_id, 
                        @PathVariable String bdate,
                        @PathVariable long bloodtype_id, 
                        @PathVariable String phone,
                        @PathVariable String address,
                        @PathVariable long disease_id) {
        Patient newPatient = new Patient();
        Gender gender = genderrepository.findById(gender_id);
        Bloodtype bloodtype = bloodtyperepository.findById(bloodtype_id);
        Disease disease = diseaserepository.findById(disease_id);
        
        newPatient.setFullname(fullname);
        newPatient.setPhone(phone);
        newPatient.setAddress(address);
        newPatient.setGender(gender);
        newPatient.setBloodtype(bloodtype);
        newPatient.setDisease(disease);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthdate = formatter.parse(bdate);
            newPatient.setBirthdate(birthdate);
        } catch (ParseException e) {
        };
        newPatient.setPatientdate(new Date());

        return patientrepository.save(newPatient);
    }
}

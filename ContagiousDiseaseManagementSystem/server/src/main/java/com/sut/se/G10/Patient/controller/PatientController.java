package com.sut.se.G10.Patient.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Register.Repository.GenderRepository;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PatientController {

    @Autowired 
    private final PatientRepository patientRepository;
    @Autowired 
    private GenderRepository genderRepository;
    @Autowired 
    private BloodtypeRepository  bloodtypeRepository;
    @Autowired 
    private DiseaseRepository diseaseRepository;

    PatientController(  PatientRepository patientRepository,
                        GenderRepository genderRepository,
                        BloodtypeRepository  bloodtypeRepository,
                        DiseaseRepository diseaseRepository) {
        this.patientRepository = patientRepository;
        this.genderRepository = genderRepository;
        this.bloodtypeRepository = bloodtypeRepository;
        this.diseaseRepository = diseaseRepository;
    }
    
    @GetMapping("/patient")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public Optional<Patient> getDisease(@PathVariable Long id) {
        return patientRepository.findById(id);
    }

    @PostMapping("/patient/{pafullname}/{gender_id}/{pabirthdate}/{bloodtype_id}/{phone}/{address}/{disease_id}")
    public Patient newPatient( Patient newPatient,
                        @PathVariable String pafullname, 
                        @PathVariable long gender_id, 
                        @PathVariable String pabirthdate,
                        @PathVariable long bloodtype_id, 
                        @PathVariable String phone,
                        @PathVariable String address,
                        @PathVariable long disease_id) {
                            
        Gender gender = genderRepository.findById(gender_id);
        Bloodtype bloodtype = bloodtypeRepository.findById(bloodtype_id);
        Disease disease = diseaseRepository.findById(disease_id);
        
        newPatient.setPatientfullname(pafullname);
        newPatient.setPhone(phone);
        newPatient.setAddress(address);
        newPatient.setGender(gender);
        newPatient.setBloodtype(bloodtype);
        newPatient.setDisease(disease);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date patientbirthdate = formatter.parse(pabirthdate);
            newPatient.setPatientbirthdate(patientbirthdate);
        } catch (ParseException e) {
        };
        newPatient.setPatientdate(new Date());

        return patientRepository.save(newPatient);
    }
}

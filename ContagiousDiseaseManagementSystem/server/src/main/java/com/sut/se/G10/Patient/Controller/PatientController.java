package com.sut.se.G10.Patient.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Entity.DiseasePatient;
import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;
import com.sut.se.G10.Patient.Repository.DiseasePatientRepository;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Register.Repository.GenderRepository;

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
    @Autowired 
    private final DiseasePatientRepository diseasePatientRepository;

    PatientController(  PatientRepository patientRepository,
                        GenderRepository genderRepository,
                        BloodtypeRepository  bloodtypeRepository,
                        DiseasePatientRepository diseasePatientRepository) {
        this.patientRepository = patientRepository;
        this.genderRepository = genderRepository;
        this.bloodtypeRepository = bloodtypeRepository;
        this.diseasePatientRepository = diseasePatientRepository;
    }
    
    @GetMapping("/patient")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    @GetMapping("/patient/{id}")
    public Optional<Patient> getDisease(@PathVariable Long id) {
        return patientRepository.findById(id);
    }

    @GetMapping("/patientByPersonId/{personId}")
    public Patient getPatientByPersonId(@PathVariable String personId) {
        return patientRepository.findByPersonId(personId);
    }

    @PostMapping("/patient/{pafullname}/{person_id}/{gender_id}/{pabirthdate}/{bloodtype_id}/{phone}/{address}/{disease_id}")
    public void newPatient( Patient newPatient,
                        @PathVariable String pafullname, 
                        @PathVariable String person_id,
                        @PathVariable long gender_id, 
                        @PathVariable String pabirthdate,
                        @PathVariable long bloodtype_id, 
                        @PathVariable String phone,
                        @PathVariable String address,
                        @PathVariable long[] disease_id) {
                            
        Gender gender = genderRepository.findById(gender_id);
        Bloodtype bloodtype = bloodtypeRepository.findById(bloodtype_id);
        
        newPatient.setPatientfullname(pafullname);
        newPatient.setPersonId(person_id);
        newPatient.setGender(gender);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date patientbirthdate = formatter.parse(pabirthdate);
            newPatient.setPatientbirthdate(patientbirthdate);
        } catch (ParseException e) {
        };
        
        newPatient.setPhone(phone);
        newPatient.setAddress(address);
        newPatient.setBloodtype(bloodtype);
        newPatient.setPatientdate(new Date());

        Patient patientForDiseasePatient = patientRepository.save(newPatient);
        for(int i=0; i<disease_id.length; i++){
            DiseasePatient diseasePatient = new DiseasePatient();
            diseasePatient.setPatient(patientForDiseasePatient);
            diseasePatient.setDisease(diseaseRepository.findById(disease_id[i]));
            diseasePatientRepository.save(diseasePatient);
        }
    }
}

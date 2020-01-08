package com.sut.se.G10.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.sut.se.G10.entity.Diagnose;
import com.sut.se.G10.entity.Admission;
import com.sut.se.G10.entity.Disease;
import com.sut.se.G10.entity.MedicalStaff;
import com.sut.se.G10.entity.Patient;
import com.sut.se.G10.repository.DiagnoseRepository;
import com.sut.se.G10.repository.AdmissionRepository;
import com.sut.se.G10.repository.DiseaseRepository;
import com.sut.se.G10.repository.MedicalStaffRepository;
import com.sut.se.G10.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class DiagnoseController {

    @Autowired private DiagnoseRepository diagnoseRepository;
    @Autowired private AdmissionRepository admissionRepository;
    @Autowired private DiseaseRepository diseaseRepository;
    @Autowired private MedicalStaffRepository medicalStaffRepository;
    @Autowired private PatientRepository patientRepository;

    @GetMapping("/diagnose")
    public List<Diagnose> findDiagnoses() {
        return diagnoseRepository.findAll();
    }

    @GetMapping("/diagnose/{id}")
    public Optional<Diagnose> findDiagnose(@PathVariable Long id) {
        return diagnoseRepository.findById(id);
    }

    @PostMapping("/diagnose/{patientId}/{medicalStaffId}/{diseaseName}/{admissionAdmitted}/{diagnosis}/{stayAlertedTime}")
    public Diagnose saveDiagnose(@PathVariable Long patientId, @PathVariable Long medicalStaffId, @PathVariable String diseaseName, @PathVariable String admissionAdmitted, @PathVariable String diagnosis, @PathVariable String stayAlertedTime, @RequestBody Map<String, String> body) {
        Diagnose newDiagnose = new Diagnose();

        Optional<Patient> patient = patientRepository.findById(patientId);
        Optional<MedicalStaff> medicalStaff = medicalStaffRepository.findById(medicalStaffId);
        Disease disease = diseaseRepository.findByName(diseaseName);
        Admission admission = admissionRepository.findByAdmitted(admissionAdmitted);
        
        newDiagnose.setPatient(patient.get());
        newDiagnose.setMedicalStaff(medicalStaff.get());
        newDiagnose.setDisease(disease);
        newDiagnose.setAdmission(admission);
        newDiagnose.setDiagnosisDate(new Date());
        newDiagnose.setDiagnosis(body.get("diagnosis").toString());
        newDiagnose.setStayAlertedTime(body.get("stayAlertedTime").toString());
        
        return diagnoseRepository.save(newDiagnose);
    }

}
package com.sut.se.G10.Diagnose.Controller;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Diagnose.Entity.Admission;
import com.sut.se.G10.Diagnose.Entity.Diagnose;
import com.sut.se.G10.Diagnose.Repository.AdmissionRepository;
import com.sut.se.G10.Diagnose.Repository.DiagnoseRepository;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;

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
public class DiagnoseController {

    @Autowired
    private final DiagnoseRepository diagnoseRepository;
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private PatientRepository patientRepository;

    DiagnoseController(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }

    @GetMapping("/diagnose")
    public List<Diagnose> diagnoses() {
        return diagnoseRepository.findAll();
    }

    @GetMapping("/diagnose/{id}")
    public Optional<Diagnose> findDiagnose(@PathVariable Long id) {
        return diagnoseRepository.findById(id);
    }

    @PostMapping("/diagnose/{patient_id}/{medicalStaff_id}/{disease_id}/{admissionAdmitted}/{diagnosis}/{stayAlertedTime}")
    public Diagnose newDiagnose( Diagnose newDiagnose,
            @PathVariable long patient_id, 
            @PathVariable long medicalStaff_id,
            @PathVariable long disease_id, 
            @PathVariable String admissionAdmitted, 
            @PathVariable String diagnosis,
            @PathVariable String stayAlertedTime) {

        Patient patientfullname = patientRepository.findById(patient_id);
        MedicalStaff fullname = medicalStaffRepository.findById(medicalStaff_id);
        Disease disease = diseaseRepository.findById(disease_id);
        Admission admission = admissionRepository.findByAdmitted(admissionAdmitted);

        newDiagnose.setPatientfullname(patientfullname);
        newDiagnose.setFullname(fullname);
        newDiagnose.setDisease(disease);
        newDiagnose.setAdmission(admission);
        newDiagnose.setDiagnosisDate(new Date());
        newDiagnose.setDiagnosis(diagnosis);
        newDiagnose.setStayAlertedTime(stayAlertedTime);

        return diagnoseRepository.save(newDiagnose);
    }

}
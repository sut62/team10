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
import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;
import com.sut.se.G10.Diagnose.Repository.BloodPressureLevelRepository;
import com.sut.se.G10.Diagnose.Entity.DiagnoseDisease;
import com.sut.se.G10.Diagnose.Repository.DiagnoseDiseaseRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

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
    @Autowired
    private BloodPressureLevelRepository bloodPressureLevelRepository;
    @Autowired
    private DiagnoseDiseaseRepository diagnoseDiseaseRepository;

    DiagnoseController(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }

    @GetMapping("/diagnose")
    public List<Diagnose> getDiagnoses() {
        return diagnoseRepository.findAll();
    }

    @GetMapping("/diagnose/{id}")
    public Optional<Diagnose> getDiagnose(@PathVariable Long id) {
        return diagnoseRepository.findById(id);
    }

    @PostMapping("/diagnose/{patient_id}/{medicalStaff_id}/{bloodPressureLevel_id}/{admission_id}/{diagnosis}/{stayAlertedTime}/{disease_Ids}")
    public void addDiagnoseAndAddDiagnoseDisease( Diagnose newDiagnose,
            @PathVariable long patient_id, 
            @PathVariable long medicalStaff_id,
            @PathVariable long bloodPressureLevel_id,
            @PathVariable long admission_id, 
            @PathVariable String diagnosis,
            @PathVariable Long stayAlertedTime,
            @PathVariable long[] disease_Ids) {

        Patient patient = patientRepository.findById(patient_id);
        MedicalStaff diagnosisDoctor = medicalStaffRepository.findById(medicalStaff_id);
        BloodPressureLevel bloodPressureLevel = bloodPressureLevelRepository.findById(bloodPressureLevel_id);
        Admission admission = admissionRepository.findById(admission_id);

        newDiagnose.setPatient(patient);
        newDiagnose.setDiagnosisDoctor(diagnosisDoctor);
        newDiagnose.setBloodPressureLevel(bloodPressureLevel);
        newDiagnose.setAdmission(admission);
        newDiagnose.setDiagnosisDate(new Date());
        newDiagnose.setDiagnoseCode(generateCode());
        newDiagnose.setDiagnosis(diagnosis);
        newDiagnose.setStayAlertedTime(stayAlertedTime);
                
        Diagnose diagnoseForDiagnoseDisease = diagnoseRepository.save(newDiagnose);

        for(int i=0; i<disease_Ids.length; i++){
            DiagnoseDisease diagnoseDisease = new DiagnoseDisease();
            diagnoseDisease.setDiagnose(diagnoseForDiagnoseDisease);
            diagnoseDisease.setDisease(diseaseRepository.findById(disease_Ids[i]));
            diagnoseDiseaseRepository.save(diagnoseDisease);
        }
    }
    
    public String generateCode() {
        String code = UUID.randomUUID().toString();
        return code;
    }

}
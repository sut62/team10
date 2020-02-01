package com.sut.se.G10;

import com.sut.se.G10.Diagnose.Entity.DiagnoseDisease;
import com.sut.se.G10.Diagnose.Repository.DiagnoseDiseaseRepository;
import com.sut.se.G10.Diagnose.Entity.Diagnose;
import com.sut.se.G10.Diagnose.Repository.DiagnoseRepository;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Diagnose.Entity.Admission;
import com.sut.se.G10.Diagnose.Repository.AdmissionRepository;
import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;
import com.sut.se.G10.Diagnose.Repository.BloodPressureLevelRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
public class DiagnoseDiseaseTests {

    private Validator validator;

    @Autowired
    private DiagnoseDiseaseRepository diagnoseDiseaseRepository;
    @Autowired
    private DiagnoseRepository diagnoseRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BloodPressureLevelRepository bloodPressureLevelRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5911837_testDiagnoseDiseaseAllCorrect() {
        DiagnoseDisease diagnoseDisease = new DiagnoseDisease();
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("ABab12 _.,");
        diagnose.setDiagnosisDate(new Date(2020, 12, 25));
        diagnose.setStayAlertedTime("3 months");
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        diagnose.setPatient(patientRepository.findById(1));
        diagnose.setDiagnosisDoctor(medicalStaffRepository.findById(1));

        diagnose =  diagnoseRepository.saveAndFlush(diagnose);
        diagnoseDisease.setDiagnose(diagnose);

        Disease disease = new Disease();
        disease = diseaseRepository.findById(1);
        disease = diseaseRepository.saveAndFlush(disease);
        diagnoseDisease.setDisease(disease);

        diagnoseDisease = diagnoseDiseaseRepository.saveAndFlush(diagnoseDisease);

        Optional<DiagnoseDisease> found = diagnoseDiseaseRepository.findById(diagnoseDisease.getId());
        assertEquals(diagnose, found.get().getDiagnose());
        assertEquals(disease, found.get().getDisease());
    }

    @Test
    void b5911837_testDiagnoseDisease_DiseaseNotNull() {
        DiagnoseDisease diagnoseDisease = new DiagnoseDisease();
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("ABab12 _.,");
        diagnose.setDiagnosisDate(new Date(2020, 12, 25));
        diagnose.setStayAlertedTime("3 months");
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        diagnose.setPatient(patientRepository.findById(1));
        diagnose.setDiagnosisDoctor(medicalStaffRepository.findById(1));

        diagnose =  diagnoseRepository.saveAndFlush(diagnose);
        diagnoseDisease.setDiagnose(diagnose);

        Disease disease = new Disease();
        disease = null;
        diagnoseDisease.setDisease(disease);

        Set<ConstraintViolation<DiagnoseDisease>> result = validator.validate(diagnoseDisease);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DiagnoseDisease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("disease", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnoseDisease_DiagnoseNotNull() {
        DiagnoseDisease diagnoseDisease = new DiagnoseDisease();
        Diagnose diagnose = new  Diagnose();
        diagnose =  null;
        diagnoseDisease.setDiagnose(diagnose);

        Disease disease = new Disease();
        disease = diseaseRepository.findById(1);
        disease = diseaseRepository.saveAndFlush(disease);
        diagnoseDisease.setDisease(disease);

        Set<ConstraintViolation<DiagnoseDisease>> result = validator.validate(diagnoseDisease);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DiagnoseDisease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnose", v.getPropertyPath().toString());
    }

}
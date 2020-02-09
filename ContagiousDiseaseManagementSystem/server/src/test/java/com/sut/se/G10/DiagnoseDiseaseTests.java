package com.sut.se.G10;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.ProvinceRepository;
import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Register.Repository.GenderRepository;
import com.sut.se.G10.Register.Entity.Position;
import com.sut.se.G10.Register.Repository.PositionRepository;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;
import com.sut.se.G10.Diagnose.Repository.BloodPressureLevelRepository;
import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;

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

import java.time.LocalDateTime;
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
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ---------------------------------- Test All Field ---------------------------------------------
    @Test
    void b5911837_testDiagnoseDiseaseAllCorrect() {
        DiagnoseDisease diagnoseDisease = new DiagnoseDisease();
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("ABab12 _.,");
        LocalDateTime dateTimeNow = LocalDateTime.now();
        diagnose.setDiagnosisDate(dateTimeNow);
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

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
        LocalDateTime dateTimeNow = LocalDateTime.now();
        diagnose.setDiagnosisDate(dateTimeNow);
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

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
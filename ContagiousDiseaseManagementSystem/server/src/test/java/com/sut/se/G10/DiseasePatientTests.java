package com.sut.se.G10;

import com.sut.se.G10.Patient.Entity.DiseasePatient;
import com.sut.se.G10.Patient.Repository.DiseasePatientRepository;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;
import com.sut.se.G10.Register.Repository.GenderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DiseasePatientTests {

    private Validator validator;

    @Autowired
    private DiseasePatientRepository diseasePatientRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;
    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ---------------------------------- Test All Field ---------------------------------------------
    @Test
    void B5910557_testDiseasePatientAllCorrect() {
        DiseasePatient diseasePatient = new DiseasePatient();
        Patient patient = new  Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(genderRepository.findById(1));
        try{
        Date date = formatter.parse("1997-01-11");
        patient.setPatientbirthdate(date);
        }catch (ParseException e) {}
        patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");        
        patient.setBloodtype(bloodtypeRepository.findById(1));
        patient.setPatientdate(new Date());

        patient = patientRepository.saveAndFlush(patient);
        diseasePatient.setPatient(patient);

        Disease disease = new Disease();
        disease = diseaseRepository.findById(1);
        disease = diseaseRepository.saveAndFlush(disease);
        diseasePatient.setDisease(disease);

        diseasePatient = diseasePatientRepository.saveAndFlush(diseasePatient);

        Optional<DiseasePatient> found = diseasePatientRepository.findById(diseasePatient.getId());
        assertEquals(patient, found.get().getPatient());
        assertEquals(disease, found.get().getDisease());
    }

    //-----------------------------NOT NULL----------------------------------
    //--------------------------DISEASE-------------------------------
    @Test
    void B5910557_testDiseasePatient_DiseaseNotNull() {
        DiseasePatient diseasePatient = new DiseasePatient();
        Patient patient = new  Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(genderRepository.findById(1));
        try{
            Date date = formatter.parse("1997-01-11");
            patient.setPatientbirthdate(date);
        }catch (ParseException e) {}
        patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");        
        patient.setBloodtype(bloodtypeRepository.findById(1));
        patient.setPatientdate(new Date());

        patient = patientRepository.saveAndFlush(patient);
        diseasePatient.setPatient(patient);

        Disease disease = new Disease();
        disease = null;
        diseasePatient.setDisease(disease);
        

        Set<ConstraintViolation<DiseasePatient>> result = validator.validate(diseasePatient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DiseasePatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("disease", v.getPropertyPath().toString());
    }

    //--------------------------PATIENT---------------------------
    @Test
    void B5910557_testDiseasePatient_PatientNotNull() {
        DiseasePatient diseasePatient = new DiseasePatient();
        Patient patient = new  Patient();
        patient =  null;
        diseasePatient.setPatient(patient);

        Disease disease = new Disease();
        disease = diseaseRepository.findById(1);
        disease = diseaseRepository.saveAndFlush(disease);
        diseasePatient.setDisease(disease);

        Set<ConstraintViolation<DiseasePatient>> result = validator.validate(diseasePatient);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<DiseasePatient> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("patient", v.getPropertyPath().toString());
    }

}
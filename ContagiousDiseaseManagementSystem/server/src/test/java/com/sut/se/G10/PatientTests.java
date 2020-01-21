package com.sut.se.G10;

import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
public class PatientTests {

    private Validator validator;

    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910557_testDateCorrect() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}

        Patient patientFound = new Patient();
        patientFound = patientRepository.save(patient);
        Optional<Patient> found = patientRepository.findById(patientFound.getId());
        try {
            assertEquals(formatter.parse("1997-09-21"), found.get().getPatientbirthdate());
        } catch (ParseException e) {}
    }

    @Test
    void B5910557_testDateMustNotBeNull() {
        Patient patient = new Patient();
        patient.setPatientbirthdate(null);
        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("birth date", v.getPropertyPath().toString());
        }
    }

    @Test
    void B5910557_testDatePatternFail() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            patient.setPatientbirthdate(date);
            patient = patientRepository.saveAndFlush(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());

            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"yyy-MM-dd\"", v.getMessage());
            assertEquals("birth date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    @Test
    void B5910557_testDateMustBeUnique() {
        Patient patient1 = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            patient1.setPatientbirthdate(date);
            patient1 = patientRepository.saveAndFlush(patient1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                Patient patient2 = new Patient();
                try {
                    Date date = formatter.parse("2563/01/21");
                    patient2.setPatientbirthdate(date);
                    patient2 = patientRepository.saveAndFlush(patient2);
                } catch (ParseException ex) {}
            });
        } catch (ParseException e) {} 
    }

    @Test
    void B5910557_testDateMustNotBe10Digits() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/211");
            patient.setPatientbirthdate(date);
            patient = patientRepository.saveAndFlush(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());

            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("birth date", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }

    //ใส่ข้อมูลถูกต้องปกติ
    @Test
	void B5910557_testPatientDataCorrect() {
        Patient patient = new Patient();
        
		patient.setPatientfullname("Wachiraya Chaiyasaj");
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        
        Patient patientFound = new Patient();
        patientFound = patientRepository.save(patient);
        Optional<Patient> found = patientRepository.findById(patientFound.getId());

        assertEquals("Wachiraya Chaiyasaj", found.get().getPatientfullname());
        assertEquals("0982208997", found.get().getPhone());
        assertEquals("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190", found.get().getAddress());
    }

    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testNameMustNotBeNull() {
        Patient patient = new Patient();
        
		patient.setPatientfullname(null);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("patientfullname", v.getPropertyPath().toString());
        }
    }
    
    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testPhoneMustNotBeNull() {
        Patient patient = new Patient();
        
		patient.setPatientfullname("Wachiraya Chaiyasaj");
		patient.setPhone(null);
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("patientfullname", v.getPropertyPath().toString());
        }
    }

    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testAddressMustNotBeNull() {
        Patient patient = new Patient();
        
		patient.setPatientfullname("Wachiraya Chaiyasaj");
		patient.setPhone("0982208997");
        patient.setAddress(null);

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("patientfullname", v.getPropertyPath().toString());
        }
    }

    //ใส่ข้อมูลไม่ถูกต้องไม่ถูกpattern
    @Test
    void  B5910557_testPhonePatternfial() {
        Patient patient = new Patient();
        patient.setPatientfullname("Wachiraya Chaiyasaj");
		patient.setPhone("098220899777");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());// result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }

    //ใส่ข้อมูลไม่ถูกต้องไม่ถูกตามsizeที่กำหนด
    @Test
    void  B5910557_testPatientFullnameSizelessthan10() {
        Patient patient = new Patient();
        patient.setPatientfullname("Wachira");
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());// result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }

}
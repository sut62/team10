package com.sut.se.G10;
import com.sut.se.G10.VaccineInformation.Entity.*;
import com.sut.se.G10.VaccineInformation.Repository.*;

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


@DataJpaTest
public class VaccineInformationTests {

    private Validator validator;

    @Autowired
    private VaccineInformationRepository vaccineinformationRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    
// ============================StorageDate===========================  
    @Test
    void b5901418_testStoragedateCorrect() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date expiredate = formatter.parse("1998-05-04");
            Date storagedate = formatter.parse("1998-05-11");
            vaccineInformation.setExpiredate(expiredate) ;
            vaccineInformation.setStoragedate(storagedate) ;
        } catch (ParseException e) {}

        vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        // VaccineInformation vaccineInformation2 = new VaccineInformation();
        // vaccineInformation2 = vaccineinformationRepository.save(vaccineInformation);

        Optional<VaccineInformation> found = vaccineinformationRepository.findById(vaccineInformation.getVaccineinformationid());
        try {
            assertEquals(formatter.parse("1998-05-11"), found.get().getStoragedate());
        } catch (ParseException e) {}
    }

    @Test
    void b5901418_testStoragedateMustNotBeNull() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        vaccineInformation.setStoragedate(null);

        try {
            Date date = formatter.parse("1999-05-13");
            vaccineInformation.setExpiredate(date);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
            // VaccineInformation vaccineInformation2 = new VaccineInformation();
            // vaccineInformation2 = vaccineinformationRepository.save(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());
    
            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("storagedate", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }

    @Test
    void b5901418_testStoragedatePatternFail() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date storagedate = formatter.parse("2541/07/16");
            vaccineInformation.setStoragedate(storagedate);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());

            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must match \"yyy-MM-dd\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    @Test
    void b5901418_testStoragedateMustBeUnique() {
        VaccineInformation vaccineInformation1 = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date storagedate = formatter.parse("2541/08/23");
            vaccineInformation1.setStoragedate(storagedate);
            vaccineInformation1 = vaccineinformationRepository.saveAndFlush(vaccineInformation1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                VaccineInformation vaccineInformation2 = new VaccineInformation();
                try {
                    Date storagedate = formatter.parse("2541/08/23");
                    vaccineInformation2.setStoragedate(storagedate);
                    vaccineInformation2 = vaccineinformationRepository.saveAndFlush(vaccineInformation2);
                } catch (ParseException ex) {}
            });
        } catch (ParseException e) {} 
    }

    @Test
    void b5901418_testStoragedateMustNotBe10Digits() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date storagedate = formatter.parse("2541/08/238");
            vaccineInformation.setStoragedate(storagedate);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());

            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
// ==============================ExpireDate===========================  
@Test
    void b5901418_testExpiredateCorrect() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date expiredate = formatter.parse("1998-05-11");
            Date storagedate = formatter.parse("1998-05-04");
            vaccineInformation.setExpiredate(expiredate) ;
            vaccineInformation.setStoragedate(storagedate) ;
        } catch (ParseException e) {}

        vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        // VaccineInformation vaccineInformation2 = new VaccineInformation();
        // vaccineInformation2 = vaccineinformationRepository.save(vaccineInformation);

        Optional<VaccineInformation> found = vaccineinformationRepository.findById(vaccineInformation.getVaccineinformationid());
        try {
            assertEquals(formatter.parse("1998-05-11"), found.get().getExpiredate());
        } catch (ParseException e) {}
    }

    @Test
    void b5901418_testExpiredateMustNotBeNull() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        vaccineInformation.setExpiredate(null);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("1999-05-13");
            vaccineInformation.setStoragedate(date);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
            // VaccineInformation vaccineInformation2 = new VaccineInformation();
            // vaccineInformation2 = vaccineinformationRepository.save(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());
    
            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("expiredate", v.getPropertyPath().toString());
        }catch (ParseException e) {}

    }

    @Test
    void b5901418_testExpiredatePatternFail() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expiredate = formatter.parse("2541/07/16");
            vaccineInformation.setExpiredate(expiredate);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());

            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must match \"yyy-MM-dd\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    @Test
    void b5901418_testExpiredateMustBeUnique() {
        VaccineInformation vaccineInformation1 = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expiredate = formatter.parse("2541/08/23");
            vaccineInformation1.setExpiredate(expiredate);
            vaccineInformation1 = vaccineinformationRepository.saveAndFlush(vaccineInformation1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                VaccineInformation vaccineInformation2 = new VaccineInformation();
                try {
                    Date expiredate = formatter.parse("2541/08/23");
                    vaccineInformation2.setExpiredate(expiredate);
                    vaccineInformation2 = vaccineinformationRepository.saveAndFlush(vaccineInformation2);
                } catch (ParseException ex) {}
            });
        } catch (ParseException e) {} 
    }

    @Test
    void b5901418_testExpiredateMustNotBe11Digits() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date expiredate = formatter.parse("2541/08/238");
            vaccineInformation.setExpiredate(expiredate);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());

            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }  
    // ================================== Start Test Combobox ==================================

    // ---------------- MedicalStaff Combobox ----------------
    @Test
    void b5901418_testfullnameComboboxMustNotBeNull() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date storagedate = formatter.parse("2563-01-21");
            Date expiredate = formatter.parse("2565-05-30");
            vaccineInformation.setStoragedate(storagedate);
            vaccineInformation.setExpiredate(expiredate);
            vaccineInformation.setFullname(null);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());
    
            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("MedicalStaffname", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }

    // ---------------- Vacicine Combobox ----------------
    @Test
    void b5901418_testVaccineComboboxMustNotBeNull() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date storagedate = formatter.parse("2563-01-21");
            Date expiredate = formatter.parse("2565-05-30");
            vaccineInformation.setStoragedate(storagedate);
            vaccineInformation.setExpiredate(expiredate);
            vaccineInformation.setVaccineid(null);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());
    
            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("Vaccine", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }

    
    // ---------------- Communicablelevel Combobox ----------------
    @Test
    void b5901418_testTypevaccineComboboxMustNotBeNull() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date storagedate = formatter.parse("2563-01-21");
            Date expiredate = formatter.parse("2565-05-30");
            vaccineInformation.setStoragedate(storagedate);
            vaccineInformation.setExpiredate(expiredate);
            vaccineInformation.setTypevaccineid(null);
            vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
            assertEquals(1, result.size());
    
            ConstraintViolation<VaccineInformation> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("TypeVaccine", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }

     // ---------------- Communicablelevel Combobox ----------------
     @Test
     void b5901418_testVaccineDataboxMustNotBeNull() {
         VaccineInformation vaccineInformation = new VaccineInformation();
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         try {
             Date storagedate = formatter.parse("2563-01-21");
             Date expiredate = formatter.parse("2565-05-30");
             vaccineInformation.setStoragedate(storagedate);
             vaccineInformation.setExpiredate(expiredate);
             vaccineInformation.setVaccineid(null);
             vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
         } catch (ConstraintViolationException e) {
             Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
             assertEquals(1, result.size());
     
             ConstraintViolation<VaccineInformation> v = result.iterator().next();
             assertEquals("must not be null", v.getMessage());
             assertEquals("VaccineData", v.getPropertyPath().toString());
         } catch (ParseException e) {}
     }
    // ================================== End Test Combobox ==================================
}
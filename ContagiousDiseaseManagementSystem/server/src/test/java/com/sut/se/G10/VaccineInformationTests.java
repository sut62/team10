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
import static org.junit.jupiter.api.Assertions.fail;

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

    
// ==============================StorageDate===========================  
    @Test
    void b5901418_testStoragedateCorrect() {
        VaccineInformation vaccineInformation = new VaccineInformation();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date storagedate = formatter.parse("1998-05-11");
            vaccineInformation.setStoragedate(storagedate) ;
        } catch (ParseException e) {}

        vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);

        Optional<VaccineInformation> found = vaccineinformationRepository.findById(vaccineInformation.getVaccineinformationid());
        try {
            assertEquals(formatter.parse("1998-05-11"), found.get().getStoragedate());
        } catch (ParseException e) {}
    }

    // @Test
    // void b5901418_testStoragedateMustNotBeNull() {
    //     VaccineInformation vaccineInformation = new VaccineInformation();
    //     vaccineInformation.setStoragedate(null);
    //     try {
            
    //         vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
    //     } catch (ConstraintViolationException e) {
    //         Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
    //         assertEquals(1, result.size());
    
    //         ConstraintViolation<VaccineInformation> v = result.iterator().next();
    //         assertEquals("must not be null", v.getMessage());
    //         assertEquals("storagedate", v.getPropertyPath().toString());
    //     }
    // }

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
            vaccineInformation.setExpiredate(expiredate) ;
        } catch (ParseException e) {}

        vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);

        Optional<VaccineInformation> found = vaccineinformationRepository.findById(vaccineInformation.getVaccineinformationid());
        try {
            assertEquals(formatter.parse("1998-05-11"), found.get().getExpiredate());
        } catch (ParseException e) {}
    }

    // @Test
    // void b5901418_testExpiredateMustNotBeNull() {
    //     VaccineInformation vaccineInformation = new VaccineInformation();
    //     vaccineInformation.setExpiredate(null);
    //     try {
    //         vaccineInformation = vaccineinformationRepository.saveAndFlush(vaccineInformation);
    //     } catch (ConstraintViolationException e) {
    //         Set<ConstraintViolation<VaccineInformation>> result = validator.validate(vaccineInformation);
    //         assertEquals(1, result.size());
    
    //         ConstraintViolation<VaccineInformation> v = result.iterator().next();
    //         assertEquals("must not be null", v.getMessage());
    //         assertEquals("expiredate", v.getPropertyPath().toString());
    //     }
    // }

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
    void b5901418_testExpiredateMustNotBe10Digits() {
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
}
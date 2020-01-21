package com.sut.se.G10;

import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;

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
public class RegisterTests {

    private Validator validator;

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     
    @Test
    void b5905492_testDateCorrect() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("1999-05-13");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}
        
        MedicalStaff medicalstaffs = new MedicalStaff();
        medicalstaffs = medicalStaffRepository.save(medicalStaff);

        Optional<MedicalStaff> found = medicalStaffRepository.findById(medicalstaffs.getId());
        try {
            assertEquals(formatter.parse("1999-05-13"), found.get().getBirthdate());
        } catch (ParseException e) {}
    }

    @Test
    void b5905492_testDateMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setBirthdate(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        }
    }

    @Test
    void b5905492_testDatePatternFail() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff.setBirthdate(date);
            medicalStaff = medicalStaffRepository.saveAndFlush(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());

            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"yyy-MM-dd\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    @Test
    void b5905492_testDateMustBeUnique() {
        MedicalStaff medicalStaff1 = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff1.setBirthdate(date);
            medicalStaff1 = medicalStaffRepository.saveAndFlush(medicalStaff1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                MedicalStaff medicalStaff2 = new MedicalStaff();
                try {
                    Date date = formatter.parse("2563/01/21");
                    medicalStaff2.setBirthdate(date);
                    medicalStaff2 = medicalStaffRepository.saveAndFlush(medicalStaff2);
                } catch (ParseException ex) {}
            });
        } catch (ParseException e) {} 
    }

    @Test
    void b5905492_testDateMustNotBe11Digits() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/211");
            medicalStaff.setBirthdate(date);
            medicalStaff = medicalStaffRepository.saveAndFlush(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());

            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
//-------------------------------------------birthdate--------------------------------------------//
}
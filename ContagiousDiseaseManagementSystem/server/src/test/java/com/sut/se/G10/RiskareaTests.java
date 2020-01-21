package com.sut.se.G10;

import com.sut.se.G10.Riskarea.Entity.Riskarea;
import com.sut.se.G10.Riskarea.Repository.RiskareaRepository;

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
public class RiskareaTests {

    private Validator validator;

    @Autowired
    private RiskareaRepository riskareaRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6020712_testDateCorrect() {
        Riskarea riskarea = new Riskarea();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("1999-05-13");
            riskarea.setDate(date) ;
        } catch (ParseException e) {}

        riskarea = riskareaRepository.saveAndFlush(riskarea);

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        try {
            assertEquals(formatter.parse("1999-05-13"), found.get().getDate());
        } catch (ParseException e) {}
    }

    @Test
    void b6020712_testDateMustNotBeNull() {
        Riskarea riskarea = new Riskarea();
        riskarea.setDate(null);
        try {
            riskarea = riskareaRepository.saveAndFlush(riskarea);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
            assertEquals(1, result.size());
    
            ConstraintViolation<Riskarea> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        }
    }

    @Test
    void b6020712_testDatePatternFail() {
        Riskarea riskarea = new Riskarea();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            riskarea.setDate(date);
            riskarea = riskareaRepository.saveAndFlush(riskarea);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
            assertEquals(1, result.size());

            ConstraintViolation<Riskarea> v = result.iterator().next();
            assertEquals("must match \"yyy-MM-dd\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    @Test
    void b6020712_testDateMustBeUnique() {
        Riskarea riskarea1 = new Riskarea();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            riskarea1.setDate(date);
            riskarea1 = riskareaRepository.saveAndFlush(riskarea1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                Riskarea riskarea2 = new Riskarea();
                try {
                    Date date = formatter.parse("2563/01/21");
                    riskarea2.setDate(date);
                    riskarea2 = riskareaRepository.saveAndFlush(riskarea2);
                } catch (ParseException ex) {}
            });
        } catch (ParseException e) {} 
    }

    @Test
    void b6020712_testDateMustNotBe10Digits() {
        Riskarea riskarea = new Riskarea();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/211");
            riskarea.setDate(date);
            riskarea = riskareaRepository.saveAndFlush(riskarea);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
            assertEquals(1, result.size());

            ConstraintViolation<Riskarea> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
}
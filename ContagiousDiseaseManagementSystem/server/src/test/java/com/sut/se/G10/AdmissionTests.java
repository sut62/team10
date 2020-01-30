package com.sut.se.G10;

import com.sut.se.G10.Diagnose.Entity.Admission;
import com.sut.se.G10.Diagnose.Repository.AdmissionRepository;

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
public class AdmissionTests {

    private Validator validator;

    @Autowired
    private AdmissionRepository admissionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5911837_testAdmissionAllCorrect() {
        Admission admission = new  Admission();
        admission.setAdmitted("XXXXX");

        admission =  admissionRepository.saveAndFlush(admission);

        Optional<Admission> found = admissionRepository.findById(admission.getId());
        assertEquals("XXXXX", found.get().getAdmitted());
    }

    @Test
    void b5911837_testAdmittedNotNull() {
        Admission admission = new  Admission();
        admission.setAdmitted(null);

        Set<ConstraintViolation<Admission>> result = validator.validate(admission);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Admission> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("admitted", v.getPropertyPath().toString());
    }

}
package com.sut.se.G10;

import com.sut.se.G10.Diagnose.Entity.Diagnose;
import com.sut.se.G10.Diagnose.Repository.DiagnoseRepository;

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
public class DiagnoseTests {

    private Validator validator;

    @Autowired
    private DiagnoseRepository diagnoseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5911837_testDiagnoseAllCorrect() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("ABab12 _.,");
        diagnose.setDiagnosisDate(new Date(2020, 12, 25));
        diagnose.setStayAlertedTime("3 months");

        diagnose =  diagnoseRepository.saveAndFlush(diagnose);

        Optional<Diagnose> found = diagnoseRepository.findById(diagnose.getId());
        assertEquals("ABab12 _.,", found.get().getDiagnosis());
        assertEquals(new Date(2020, 12, 25), found.get().getDiagnosisDate());
        assertEquals("3 months", found.get().getStayAlertedTime());
    }

    @Test
    void b5911837_testDiagnosisDateNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(null);
        diagnose.setStayAlertedTime("3 months");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnosisDate", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisNotAgianstPattern() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("123456789$");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("3 months");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z0-9._, \t]*\"", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis(null);
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("3 months");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisMin10() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("123456789");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("3 months");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 10 and 100", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisMax100() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("12345678901234567890123456789012345678901234567890"+
        "123456789012345678901234567890123456789012345678901");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("3 months");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 10 and 100", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testStayAlertedTimeNotAgianstPattern() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("1_months");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z0-9 \t]*\"", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testStayAlertedTimeNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(null);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testStayAlertedTimeMin5() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("1234");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testStayAlertedTimeMax20() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime("123456789012345678901");

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 5 and 20", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

}
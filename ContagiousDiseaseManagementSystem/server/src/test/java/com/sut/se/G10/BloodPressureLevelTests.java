package com.sut.se.G10;

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
public class BloodPressureLevelTests {

    private Validator validator;

    @Autowired
    private BloodPressureLevelRepository bloodPressureLevelRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5911837_testBloodPressureLevelAllCorrect() {
        BloodPressureLevel bloodPressureLevel = new BloodPressureLevel();
        bloodPressureLevel.setLevel("XXXXX");

        bloodPressureLevel =  bloodPressureLevelRepository.saveAndFlush(bloodPressureLevel);

        Optional<BloodPressureLevel> found = bloodPressureLevelRepository.findById(bloodPressureLevel.getId());
        assertEquals("XXXXX", found.get().getLevel());
    }

    @Test
    void b5911837_testLevelNotNull() {
        BloodPressureLevel bloodPressureLevel = new  BloodPressureLevel();
        bloodPressureLevel.setLevel(null);

        Set<ConstraintViolation<BloodPressureLevel>> result = validator.validate(bloodPressureLevel);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<BloodPressureLevel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("level", v.getPropertyPath().toString());
    }

}
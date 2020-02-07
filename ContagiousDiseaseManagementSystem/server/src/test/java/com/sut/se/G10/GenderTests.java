package com.sut.se.G10;

import com.sut.se.G10.Register.Entity.Gender;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class GenderTests {

    private Validator validator;

    @Autowired
    private GenderRepository genderRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910557_testGenderAllCorrect() {
        Gender gender = new Gender();
        gender.setGender("XXX");

        gender =  genderRepository.saveAndFlush(gender);

        Optional<Gender> found = genderRepository.findById(gender.getId());
        assertEquals("XXX", found.get().getGender());
    }

    @Test
    void B5910557_testGenderNotNull() {
        Gender gender = new Gender();
        gender.setGender(null);

        Set<ConstraintViolation<Gender>> result = validator.validate(gender);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Gender> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("gender", v.getPropertyPath().toString());
    }
}
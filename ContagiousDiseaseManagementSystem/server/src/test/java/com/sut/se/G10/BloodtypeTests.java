package com.sut.se.G10;

import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;

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
public class BloodtypeTests {

    private Validator validator;

    @Autowired
    private BloodtypeRepository bloodtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5910557_testBloodtypeAllCorrect() {
        Bloodtype bloodtype = new Bloodtype();
        bloodtype.setBloodtype("XX");

        bloodtype =  bloodtypeRepository.saveAndFlush(bloodtype);

        Optional<Bloodtype> found = bloodtypeRepository.findById(bloodtype.getId());
        assertEquals("XX", found.get().getBloodtype());
    }

    @Test
    void B5910557_testBloodtypeNotNull() {
        Bloodtype bloodtype = new Bloodtype();
        bloodtype.setBloodtype(null);

        Set<ConstraintViolation<Bloodtype>> result = validator.validate(bloodtype);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Bloodtype> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bloodtype", v.getPropertyPath().toString());
    }
}
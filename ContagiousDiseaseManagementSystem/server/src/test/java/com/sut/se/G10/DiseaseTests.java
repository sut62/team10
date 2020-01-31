package com.sut.se.G10;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;

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
public class DiseaseTests {

    private Validator validator;

    @Autowired
    private DiseaseRepository diseaseRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5911837_testDiseaseAllCorrect() {
        Disease disease = new  Disease();
        disease.setDisease("XXXXX");

        disease =  diseaseRepository.saveAndFlush(disease);

        Optional<Disease> found = diseaseRepository.findById(disease.getId());
        assertEquals("XXXXX", found.get().getDisease());
    }

    @Test
    void b5911837_testDiseaseNotNull() {
        Disease disease = new  Disease();
        disease.setDisease(null);

        Set<ConstraintViolation<Disease>> result = validator.validate(disease);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Disease> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("disease", v.getPropertyPath().toString());
    }

}
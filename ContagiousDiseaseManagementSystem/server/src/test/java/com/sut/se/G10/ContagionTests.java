package com.sut.se.G10;

import com.sut.se.G10.Contagion.Entity.Contagion;
import com.sut.se.G10.Contagion.Repository.ContagionRepository;

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
public class ContagionTests {

    private Validator validator;

    @Autowired
    private ContagionRepository contagionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5913480_testContagionCorrect() {
        Contagion contagion = new Contagion();
        contagion.setCarrier("virus");

        Contagion contagionFound = new Contagion();
        contagionFound =  contagionRepository.save(contagion);

        Optional<Contagion> found = contagionRepository.findById(contagionFound.getId());
        assertEquals("virus", found.get().getCarrier());
    }

    @Test
    void b5913480_testCarrierMustNotBe11Character() {
        Contagion contagion = new Contagion();
        try {
            contagion.setCarrier("abcdefghijk");
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Contagion> message = result.iterator().next();
            assertEquals("must match \"[a-z]{3,10}$\"", message.getMessage());
            assertEquals("carrier", message.getPropertyPath().toString());
        }
    }

    @Test
    void b5913480_testCarrierWrongPattern() {
        Contagion contagion = new Contagion();
        try {
            contagion.setCarrier("@#$%()");
        } catch (ConstraintViolationException e){
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Contagion> message = result.iterator().next();
            assertEquals("must match size must be a-z", message.getMessage());
            assertEquals("carrier", message.getPropertyPath().toString());
        }
    }

    @Test
    void b5913480_testMustNotBeNull() {
        Contagion contagion = new Contagion();
        try {
            contagion.setCarrier(null);
        } catch (ConstraintViolationException e){
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Contagion> message = result.iterator().next();
            assertEquals("must not be null", message.getMessage());
            assertEquals("carrier", message.getPropertyPath().toString());
        }
    }
}
package com.sut.se.G10;

import com.sut.se.G10.Riskarea.Entity.Communicablelevel;
import com.sut.se.G10.Riskarea.Repository.CommunicablelevelRepository;

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
public class CommunicablelevelTests {

    private Validator validator;

    @Autowired
    private CommunicablelevelRepository communicablelevelRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6020712_testCommunicablelevelCorrect() {
        Communicablelevel communicablelevel = new Communicablelevel();
        communicablelevel.setCommunicablelevel("ระบาดเล็กน้อย (น้อยกว่า 10 คน)");

        communicablelevel = communicablelevelRepository.saveAndFlush(communicablelevel);

        Optional<Communicablelevel> found = communicablelevelRepository.findById(communicablelevel.getId());
        assertEquals("ระบาดเล็กน้อย (น้อยกว่า 10 คน)", found.get().getCommunicablelevel());
    }

    @Test
    void b6020712_testCommunicablelevelMustNotBeNull() {
        Communicablelevel communicablelevel = new Communicablelevel();
        communicablelevel.setCommunicablelevel(null);

        Set<ConstraintViolation<Communicablelevel>> result = validator.validate(communicablelevel);
        assertEquals(1, result.size());

        ConstraintViolation<Communicablelevel> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("communicablelevel", v.getPropertyPath().toString());
    }

}
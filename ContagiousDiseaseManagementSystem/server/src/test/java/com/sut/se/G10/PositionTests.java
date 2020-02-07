package com.sut.se.G10;

import com.sut.se.G10.Register.Entity.Position;
import com.sut.se.G10.Register.Repository.PositionRepository;

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
public class PositionTests {

    private Validator validator;

    @Autowired
    private PositionRepository positionRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5905492_testPositionAllCorrect() {
        Position position = new Position();
        position.setPosition("Doctor");

        position =  positionRepository.saveAndFlush(position);

        Optional<Position> found = positionRepository.findById(position.getId());
        assertEquals("Doctor", found.get().getPosition());
    }

    @Test
    void B5905492_testPositionNotNull() {
        Position position = new Position();
        position.setPosition(null);

        Set<ConstraintViolation<Position>> result = validator.validate(position);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Position> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("position", v.getPropertyPath().toString());
    }

}
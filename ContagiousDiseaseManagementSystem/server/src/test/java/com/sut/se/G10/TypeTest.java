package com.sut.se.G10;

import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Contagion.Repository.TypeRepository;

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
public class TypeTest {

    private Validator validator;

    @Autowired
    private TypeRepository typeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b5913480_testTypeAllCorrect() {
        Type type = new Type();
        type.setType("กลุ่มเชื้อโรคที่ผ่านทางบาดแผลหรือเยื่อบุผิวหนัง");

        type =  typeRepository.saveAndFlush(type);

        Optional<Type> found = typeRepository.findById(type.getId());
        assertEquals("กลุ่มเชื้อโรคที่ผ่านทางบาดแผลหรือเยื่อบุผิวหนัง", found.get().getType());
    }

    @Test
    void b5913480_testTypeNotNull() {
        Type type = new Type();
        type.setType(null);

        Set<ConstraintViolation<Type>> result = validator.validate(type);

        assertEquals(1, result.size());

        ConstraintViolation<Type> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("type", v.getPropertyPath().toString());
    }

}
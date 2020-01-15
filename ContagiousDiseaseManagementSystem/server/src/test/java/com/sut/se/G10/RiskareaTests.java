package com.sut.se.G10;

import com.sut.se.G10.Riskarea.Entity.Riskarea;
import com.sut.se.G10.Riskarea.Repository.RiskareaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class RiskareaTests {

    private Validator validator;

    @Autowired
    private RiskareaRepository riskareaRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void b6020712_testDateOKWithCorrectPattern() {
        Riskarea riskarea = new Riskarea();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("1999-05-13");
            riskarea.setDate(date) ;
        } catch (ParseException e) {
        }

        riskarea = riskareaRepository.saveAndFlush(riskarea);

        // Optional<Riskarea> found = riskareaRepository.findById(riskarea.parse().getDate());
        // assertEquals("1999-05-13", found.get().getDate());
        }
}
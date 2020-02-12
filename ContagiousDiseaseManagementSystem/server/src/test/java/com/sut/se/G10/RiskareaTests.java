package com.sut.se.G10;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.ProvinceRepository;
import com.sut.se.G10.Riskarea.Entity.Communicablelevel;
import com.sut.se.G10.Riskarea.Entity.Riskarea;
import com.sut.se.G10.Riskarea.Repository.CommunicablelevelRepository;
import com.sut.se.G10.Riskarea.Repository.RiskareaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class RiskareaTests {

    private Validator validator;

    @Autowired
    private RiskareaRepository riskareaRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private CommunicablelevelRepository communicablelevelRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // RISKAREA CAN SAVE
    @Test
    void b6020712_testRiskareaCanSave() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Disease disease = diseaseRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);
        LocalDate date = LocalDate.now();

        riskarea.setProvince(province);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(date);

        riskarea = riskareaRepository.saveAndFlush(riskarea);

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(province, found.get().getProvince());
        assertEquals(disease, found.get().getDisease());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());
        assertEquals(LocalDate.now(), found.get().getDate());
    }

    // RISKAREA DATE MUST NOT BE NULL
    @Test
    void b6020712_testRiskareaDateMustNotBeNull() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Disease disease = diseaseRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);

        riskarea.setProvince(province);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(null);
        riskareaRepository.save(riskarea);

        Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
        assertEquals(1, result.size());

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(province, found.get().getProvince());
        assertEquals(disease, found.get().getDisease());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());

        ConstraintViolation<Riskarea> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }

    // RISKAREA DATE MUST NOT BE PAST
    @Test
    void b6020712_testRiskareaDateMustNotBePast() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Disease disease = diseaseRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);
        LocalDate date = LocalDate.parse("2000-02-09");

        riskarea.setProvince(province);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(date);
        riskareaRepository.save(riskarea);

        Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
        assertEquals(1, result.size());

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(province, found.get().getProvince());
        assertEquals(disease, found.get().getDisease());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());

        ConstraintViolation<Riskarea> v = result.iterator().next();
        assertEquals("must be a date in the present or in the future", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }

    // RISKAREA DATE MUST NOT BE FUTURE
    @Test
    void b6020712_testRiskareaDateMustNotBeFuture() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Disease disease = diseaseRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);
        LocalDate date = LocalDate.parse("2030-02-09");

        riskarea.setProvince(province);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(date);
        riskareaRepository.save(riskarea);

        Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
        assertEquals(1, result.size());

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(province, found.get().getProvince());
        assertEquals(disease, found.get().getDisease());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());

        ConstraintViolation<Riskarea> v = result.iterator().next();
        assertEquals("must be a date in the past or in the present", v.getMessage());
        assertEquals("date", v.getPropertyPath().toString());
    }

    // For Test Pattern Of Date
    boolean CheckDatePattern(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setLenient(false);
        try {
            formatter.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    // TEST DATE PATTERN IS TRUE
    @Test
    void b6020712_testRiskareaDatePatternIsTrue() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Disease disease = diseaseRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);
        System.out.println("This is date after format: " + date.toString());

        riskarea.setProvince(province);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(date);
        riskareaRepository.save(riskarea);

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());

        assertEquals(province, found.get().getProvince());
        assertEquals(disease, found.get().getDisease());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());
        System.out.println("CheckDatePattern = " + CheckDatePattern(found.get().getDate().format(formatter)));
        assertTrue(CheckDatePattern(found.get().getDate().format(formatter)));
    }

    // TEST PROVINCE MUST NOT BE NULL
    @Test
    void b6020712_testProvinceMustNotBeNull() {
        Riskarea riskarea = new Riskarea();
        Disease disease = diseaseRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);
        LocalDate date = LocalDate.now();

        riskarea.setProvince(null);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(date);
        riskareaRepository.save(riskarea);

        Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
        assertEquals(1, result.size());

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(disease, found.get().getDisease());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());
        assertEquals(LocalDate.now(), found.get().getDate());

        ConstraintViolation<Riskarea> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("province", v.getPropertyPath().toString());
    }

    // TEST DISEASE MUST NOT BE NULL
    @Test
    void b6020712_testDiseaseMustNotBeNull() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Communicablelevel communicablelevel = communicablelevelRepository.findById(1);
        LocalDate date = LocalDate.now();

        riskarea.setProvince(province);
        riskarea.setDisease(null);
        riskarea.setCommunicablelevel(communicablelevel);
        riskarea.setDate(date);
        riskareaRepository.save(riskarea);

        Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
        assertEquals(1, result.size());

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(province, found.get().getProvince());
        assertEquals(communicablelevel, found.get().getCommunicablelevel());
        assertEquals(LocalDate.now(), found.get().getDate());

        ConstraintViolation<Riskarea> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("disease", v.getPropertyPath().toString());
    }

    // TEST COMMUNICABLELEVEL MUST NOT BE NULL
    @Test
    void b6020712_testCommunicablelevelMustNotBeNull() {
        Riskarea riskarea = new Riskarea();
        Province province = provinceRepository.findById(1);
        Disease disease = diseaseRepository.findById(1);
        LocalDate date = LocalDate.now();

        riskarea.setProvince(province);
        riskarea.setDisease(disease);
        riskarea.setCommunicablelevel(null);
        riskarea.setDate(date);
        riskareaRepository.save(riskarea);

        Set<ConstraintViolation<Riskarea>> result = validator.validate(riskarea);
        assertEquals(1, result.size());

        Optional<Riskarea> found = riskareaRepository.findById(riskarea.getId());
        assertEquals(province, found.get().getProvince());
        assertEquals(disease, found.get().getDisease());
        assertEquals(LocalDate.now(), found.get().getDate());

        ConstraintViolation<Riskarea> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("communicablelevel", v.getPropertyPath().toString());
    }
}
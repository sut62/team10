package com.sut.se.G10;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.sut.se.G10.Statistics.Entity.Statistics;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Contagion.Repository.TypeRepository;
import com.sut.se.G10.Register.Repository.ProvinceRepository;
import com.sut.se.G10.Statistics.Repository.StatisticsRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
public class StatisticsTests {

    private Validator validator;

    @Autowired
    private StatisticsRepository statisticsRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ================================ Start Test Combobox =================================
    @Test
    void B5913480_testStatisticsComplete() {
        Statistics statistics = new Statistics(); 
        Disease disease = diseaseRepository.findById(1);
        Type type = typeRepository.findById(1);
        Province province = provinceRepository.findById(1);
        
        statistics.setDisease(disease);
        statistics.setType(type);
        statistics.setProvince(province);
        statistics.setRates("200");

        statistics = statisticsRepository.saveAndFlush(statistics);

        Optional<Statistics> found = statisticsRepository.findById(statistics.getId());
        assertEquals(disease, found.get().getDisease());
        assertEquals(type, found.get().getType());
        assertEquals("200", found.get().getRates());
        assertEquals(province, found.get().getProvince());
    }

    //------------------------------ Disease Combobox --------------------------------------
    @Test
    void b5913480_testDiseaseComboboxMustNotBeNull() {
        Statistics statistics = new Statistics();
 
        try {
            statistics.setRates("200");
            statistics.setDisease(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);

            assertEquals(1, result.size());
    
            ConstraintViolation<Statistics> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("disease", v.getPropertyPath().toString());
        }
    }

    // ------------------------------ Type Combobox --------------------------------------
    @Test
    void b5913480_testTypeComboboxMustNotBeNull() {
        Statistics statistics = new Statistics();
        
        try {
            statistics.setRates("200");
            statistics.setType(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);
            assertEquals(1, result.size());
    
            ConstraintViolation<Statistics> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("type", v.getPropertyPath().toString());
        }
    }

    // ------------------------------ Province Combobox --------------------------------------
    @Test
    void b5913480_testProvinceComboboxMustNotBeNull() {
        Statistics statistics = new Statistics();
        
        try {
            statistics.setRates("200");
            statistics.setProvince(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);
            assertEquals(1, result.size());
    
            ConstraintViolation<Statistics> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("province", v.getPropertyPath().toString());
        }
    }

    // ===================================== Start Test Rates ===================================
    @Test
    void b5913480_testRatesCorrect() {
        Statistics statistics = new Statistics();
        statistics.setRates("200");

        Statistics statisticsFound = new Statistics();
        statisticsFound =  statisticsRepository.save(statistics);

        Optional<Statistics> found = statisticsRepository.findById(statisticsFound.getId());
        assertEquals("200", found.get().getRates());
    }

    //null
    @Test
    void b5913480_testRatesrMustNotBeNull() {
        Statistics statistics = new Statistics();
        try {
            statistics.setRates(null);
        } catch (ConstraintViolationException e){
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());
    
        // error message ตรงชนิด และถูก field
        ConstraintViolation<Statistics> message = result.iterator().next();
        assertEquals("must not be null", message.getMessage());
        assertEquals("rates", message.getPropertyPath().toString());
        }
    }

    //min=2
    @Test
    void b5913480_testRatesMin2() {
        Statistics statistics = new Statistics();
        try {
            statistics.setRates("0");
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Statistics> message = result.iterator().next();
            assertEquals("size must be between 2 and 10", message.getMessage());
            assertEquals("rates", message.getPropertyPath().toString());
        }
    }

    //max=10
    @Test
    void b5913480_testRatesMax10() {
        Statistics statistics = new Statistics();
        try {
            statistics.setRates("20000000000");
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Statistics> message = result.iterator().next();
            assertEquals("size must be between 2 and 10", message.getMessage());
            assertEquals("rates", message.getPropertyPath().toString());
        }
    }

    //WrongPattern
    @Test
    void b5913480_testRatesWrongPattern() {
        Statistics statistics = new Statistics();
        try {
            statistics.setRates("DA%&");
        } catch (ConstraintViolationException e){
            Set<ConstraintViolation<Statistics>> result = validator.validate(statistics);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Statistics> message = result.iterator().next();
            assertEquals("must match \"[a-z0-9]*\"", message.getMessage());
            assertEquals("rates", message.getPropertyPath().toString());
        }
    }

}
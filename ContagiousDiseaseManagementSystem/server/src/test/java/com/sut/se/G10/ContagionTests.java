package com.sut.se.G10;

import com.sut.se.G10.Contagion.Entity.Contagion;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Entity.Heal;
import com.sut.se.G10.Contagion.Entity.Rate;
import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Contagion.Entity.Symptom;
import com.sut.se.G10.Contagion.Repository.ContagionRepository;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Contagion.Repository.HealRepository;
import com.sut.se.G10.Contagion.Repository.RateRepository;
import com.sut.se.G10.Contagion.Repository.SymptomRepository;
import com.sut.se.G10.Contagion.Repository.TypeRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ContagionTests {

    private Validator validator;

    @Autowired
    private ContagionRepository contagionRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private TypeRepository typeRepository;
    @Autowired
    private SymptomRepository symptomRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private HealRepository healRepository;
    

    @BeforeEach
    public void setup() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ================================ Start Test Combobox =================================
    @Test
    void B5913480_testContagionComplete() {
        Contagion contagion = new Contagion(); 
        Disease disease = diseaseRepository.findById(1);
        Type type = typeRepository.findById(1);
        Symptom symptom = symptomRepository.findById(1);
        Rate rate = rateRepository.findById(1);
        Heal heal = healRepository.findById(1);

        contagion.setDisease(disease);
        contagion.setType(type);
        contagion.setSymptom(symptom);
        contagion.setRate(rate);
        contagion.setHeal(heal);
        contagion.setCarrier("carrier");

        contagion = contagionRepository.saveAndFlush(contagion);

        Optional<Contagion> found = contagionRepository.findById(contagion.getId());
        assertEquals(disease, found.get().getDisease());
        assertEquals(type, found.get().getType());
        assertEquals("carrier", found.get().getCarrier());
        assertEquals(symptom, found.get().getSymptom());
        assertEquals(rate, found.get().getRate());
        assertEquals(heal, found.get().getHeal());
    }
    
    //------------------------------ Disease Combobox --------------------------------------
    @Test
    void b5913480_testDiseaseComboboxMustNotBeNull() {
        Contagion contagion = new Contagion();
 
        try {
            contagion.setDisease(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);

            assertEquals(1, result.size());
    
            ConstraintViolation<Contagion> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("disease", v.getPropertyPath().toString());
        }
    }

    // ------------------------------ Type Combobox --------------------------------------
    @Test
    void b5913480_testTypeComboboxMustNotBeNull() {
        Contagion contagion = new Contagion();
        
        try {
            contagion.setCarrier("bbooss");
            contagion.setType(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            assertEquals(1, result.size());
    
            ConstraintViolation<Contagion> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("type", v.getPropertyPath().toString());
        }
    }

    // ------------------------------ Symptom Combobox ---------------------------
    @Test
    void b5913480_testSymptomComboboxMustNotBeNull() {
        Contagion contagion = new Contagion();
 
        try {
            contagion.setSymptom(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            assertEquals(1, result.size());
    
            ConstraintViolation<Contagion> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("symptom", v.getPropertyPath().toString());
        }
    }

    // ------------------------------ Rate Combobox ------------------------------
    @Test
    void b5913480_testRateComboboxMustNotBeNull() {
        Contagion contagion = new Contagion();
 
        try {
            contagion.setRate(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            assertEquals(1, result.size());
    
            ConstraintViolation<Contagion> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("rate", v.getPropertyPath().toString());
        }
    }

    // ------------------------------ Heal Combobox ------------------------------
    @Test
    void b5913480_testHealComboboxMustNotBeNull() {
        Contagion contagion = new Contagion();
 
        try {
            contagion.setHeal(null);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            assertEquals(1, result.size());
    
            ConstraintViolation<Contagion> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("heal", v.getPropertyPath().toString());
        }
    }

    // ===================================== Start Test Carrier ===================================
    @Test
    void b5913480_testCarrierCorrect() {
        Contagion contagion = new Contagion();
        contagion.setCarrier("virus");

        Contagion contagionFound = new Contagion();
        contagionFound =  contagionRepository.save(contagion);

        Optional<Contagion> found = contagionRepository.findById(contagionFound.getId());
        assertEquals("virus", found.get().getCarrier());
    }

    @Test
    void b5913480_testCarrierMustNotBeNull() {
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

    @Test
    void b5913480_testCarrierMin3() {
        Contagion contagion = new Contagion();
        try {
            contagion.setCarrier("ab");
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Contagion> message = result.iterator().next();
            assertEquals("size must be between 3 and 10", message.getMessage());
            assertEquals("carrier", message.getPropertyPath().toString());
        }
    }

    @Test
    void b5913480_testCarrierMax103() {
        Contagion contagion = new Contagion();
        try {
            contagion.setCarrier("abcdefghijk");
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Contagion> message = result.iterator().next();
            assertEquals("size must be between 3 and 10", message.getMessage());
            assertEquals("carrier", message.getPropertyPath().toString());
        }
    }

    @Test
    void b5913480_testCarrierWrongPattern() {
        Contagion contagion = new Contagion();
        try {
            contagion.setCarrier("VIRUS");
        } catch (ConstraintViolationException e){
            Set<ConstraintViolation<Contagion>> result = validator.validate(contagion);
            
            // result ต้องมี error 1 ค่าเท่านั้น
            assertEquals(1, result.size());
    
            // error message ตรงชนิด และถูก field
            ConstraintViolation<Contagion> message = result.iterator().next();
            assertEquals("must match \"[a-z]*\"", message.getMessage());
            assertEquals("carrier", message.getPropertyPath().toString());
        }
    }
    
}
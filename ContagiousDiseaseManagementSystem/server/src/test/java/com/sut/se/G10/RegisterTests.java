package com.sut.se.G10;

import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Entity.Position;
import com.sut.se.G10.Register.Repository.*;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;

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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class RegisterTests {

    private Validator validator;

    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private ProvinceRepository provinceRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

     
    @Test
    void b5905492_testDateCorrect() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse("1999-05-13");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}
        
        MedicalStaff medicalstaffs = new MedicalStaff();
        medicalstaffs = medicalStaffRepository.save(medicalStaff);

        Optional<MedicalStaff> found = medicalStaffRepository.findById(medicalstaffs.getId());
        try {
            assertEquals(formatter.parse("1999-05-13"), found.get().getBirthdate());
        } catch (ParseException e) {}
    }

    @Test
    void b5905492_testDateMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setBirthdate(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        }
    }

    @Test
    void b5905492_testDatePatternFail() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff.setBirthdate(date);
            medicalStaff = medicalStaffRepository.saveAndFlush(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());

            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"yyy-MM-dd\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    @Test
    void b5905492_testDateMustBeUnique() {
        MedicalStaff medicalStaff1 = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff1.setBirthdate(date);
            medicalStaff1 = medicalStaffRepository.saveAndFlush(medicalStaff1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                MedicalStaff medicalStaff2 = new MedicalStaff();
                try {
                    Date date = formatter.parse("2563/01/21");
                    medicalStaff2.setBirthdate(date);
                    medicalStaff2 = medicalStaffRepository.saveAndFlush(medicalStaff2);
                } catch (ParseException ex) {}
            });
        } catch (ParseException e) {} 
    }

    @Test
    void b5905492_testDateMustNotBe11Digits() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563/01/211");
            medicalStaff.setBirthdate(date);
            medicalStaff = medicalStaffRepository.saveAndFlush(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());

            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
//-------------------------------------------birthdate--------------------------------------------//
@Test
    void b5905492_testFullnameMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setFullname(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("fullname", v.getPropertyPath().toString());
        }
        }
        //-------------------------------------------fullname notnull--------------------------------------------// 
        @Test
    void b5905492_testAddressMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setAddress(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("address", v.getPropertyPath().toString());
        }
}
//-------------------------------------------address notnull--------------------------------------------//
@Test
void b5905492_testEmailMustNotBeNull() {
    MedicalStaff medicalStaff1 = new MedicalStaff();

    try {
        medicalStaff1.setEmail(null);
        medicalStaff1 = medicalStaffRepository.save(medicalStaff1);
    } catch (ConstraintViolationException e) {
        Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff1);
        assertEquals(1, result.size());

        ConstraintViolation<MedicalStaff> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("email", v.getPropertyPath().toString());
        
    }
}
//-------------------------------------------email notnull--------------------------------------------//

    @Test
    void b5905492_testEmailMustBeUnique() {
        MedicalStaff medicalStaff1 = new MedicalStaff();

        try {
            medicalStaff1.setEmail("sasithon@gmail.com");
            medicalStaff1 = medicalStaffRepository.save(medicalStaff1);
        } catch (DataIntegrityViolationException e) {
            assertThrows(DataIntegrityViolationException.class, () -> {
                MedicalStaff medicalStaff2 = new MedicalStaff();
                try {
                    medicalStaff2.setEmail("sasithongmail.com");
                    medicalStaff2 = medicalStaffRepository.saveAndFlush(medicalStaff2);
                } catch (DataIntegrityViolationException ex) {}
            });
        }
    }
    //-------------------------------------------email unique--------------------------------------------//
    @Test
void b5905492_testPasswordMustNotBeNull() {
    MedicalStaff medicalStaff1 = new MedicalStaff();

    try {
        medicalStaff1.setPassword(null);
        medicalStaff1 = medicalStaffRepository.save(medicalStaff1);
    } catch (ConstraintViolationException e) {
        Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff1);
        assertEquals(1, result.size());

        ConstraintViolation<MedicalStaff> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
        
    }
}
//-------------------------------------------password notnull--------------------------------------------//
@Test
    void b5905492_testPasswordMustNotBe7Digits() {
        MedicalStaff medicalStaff = new MedicalStaff();
        try {
            
            medicalStaff.setPassword("1234567");
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());

            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\min=8\"", v.getMessage());
            assertEquals("password", v.getPropertyPath().toString());
        }
    }
    //-------------------------------------------password size--------------------------------------------//
@Test
    void b5905492_testGenderMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setGender(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("gender", v.getPropertyPath().toString());
        }
}
//-------------------------------------------gender notnull--------------------------------------------//
@Test
    void b5905492_testPositionMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setPosition(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("position", v.getPropertyPath().toString());
        }
}
//-------------------------------------------position notnull--------------------------------------------//
@Test
    void b5905492_testProvinceMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        medicalStaff.setProvince(null);
        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("province", v.getPropertyPath().toString());
        }
}
//-------------------------------------------province notnull--------------------------------------------//
@Test
void B5905492_testMedicalStaffDataCorrect() {
    MedicalStaff medicalStaff = new MedicalStaff();
    Position position = positionRepository.findById(1);
    Gender gender = genderRepository.findById(1);
    Province province = provinceRepository.findById(1);
    
    medicalStaff.setFullname("Sasithon Chairat");
    medicalStaff.setGender(gender);
    medicalStaff.setPosition(position);
    medicalStaff.setPhone("0987458748");
    medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
    medicalStaff.setProvince(province);
    medicalStaff.setEmail("sasithon@gmail.com");
    medicalStaff.setPassword("12345678");
    
    MedicalStaff medicalStaffFound = new MedicalStaff();
    medicalStaffFound = medicalStaffRepository.save(medicalStaff);
    Optional<MedicalStaff> found = medicalStaffRepository.findById(medicalStaffFound.getId());

    assertEquals("Sasithon Chairat", found.get().getFullname());
    assertEquals(gender, found.get().getGender());
    assertEquals(position, found.get().getPosition());
    assertEquals("0987458748", found.get().getPhone());
    assertEquals("97/3 หนองหาน อุดรธานี 41130", found.get().getAddress());
    assertEquals(province, found.get().getProvince());
    assertEquals("sasithon@gmail.com", found.get().getEmail());
    assertEquals("12345678", found.get().getPassword());

}
    }
//-------------------------------------------ข้อมูลถูกต้องปกติ--------------------------------------------//
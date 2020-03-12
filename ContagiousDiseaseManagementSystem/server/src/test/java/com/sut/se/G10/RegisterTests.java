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


//-------------------------------------------medicalStaff correct--------------------------------------------//
@Test
void B5905492_testMedicalStaffDataCorrect() {
    MedicalStaff medicalStaff = new MedicalStaff();
    Position position = positionRepository.findById(1);
    Gender gender = genderRepository.findById(1);
    Province province = provinceRepository.findById(1);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    
    medicalStaff.setFullname("Sasithon Chairat");
    medicalStaff.setGender(gender);
    medicalStaff.setPosition(position);
    medicalStaff.setPhone("0987458748");
    medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
    medicalStaff.setProvince(province);
    medicalStaff.setEmail("sasithon@gmail.com");
    medicalStaff.setPassword("12345678");
 	try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date);
        } catch (ParseException e) {}

    
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
	try {
            assertEquals(formatter.parse("1997/05/04"), found.get().getBirthdate());
        } catch (ParseException e) {}

}
    
//-------------------------------------------birthdate notnull--------------------------------------------//
    @Test
    void B5905492_testBirthdateMustNotBeNull() {
         MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
        Gender gender = genderRepository.findById(1);
        Province province = provinceRepository.findById(1);
        

        medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
	    medicalStaff.setBirthdate(null);
        medicalStaff.setEmail("sasithon@gmail.com");
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
//-------------------------------------------fullname notnull--------------------------------------------//
@Test
    void B5905492_testFullnameMustNotBeNull() {
       MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	 Gender gender = genderRepository.findById(1);
   	 Province province = provinceRepository.findById(1);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        medicalStaff.setFullname(null);
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}


        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("fullname", v.getPropertyPath().toString());
        }
    }
//-------------------------------------------address notnull--------------------------------------------//
        @Test
    void B5905492_testAddressMustNotBeNull() {
       MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
 	
	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress(null);
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}


        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("address", v.getPropertyPath().toString());
        }
    }
//-------------------------------------------email notnull--------------------------------------------//
@Test
void B5905492_testEmailMustNotBeNull() {
    MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail(null);
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}


        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("email", v.getPropertyPath().toString());
        }
    }
    //-------------------------------------------password notnull--------------------------------------------//
    @Test
	void B5905492_testPasswordMustNotBeNull() {
     	MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword(null);
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}


        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("password", v.getPropertyPath().toString());
        }
    }
//-----
//-------------------------------------------password size--------------------------------------------//
@Test
    void B5905492_testPasswordMustNotBe7Digits() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MMdd");

	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("1234567");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}
        

        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\min=8\"", v.getMessage());
            assertEquals("password", v.getPropertyPath().toString());
        }
    }
    //-------------------------------------------phone notnull--------------------------------------------//
    @Test
    void B5905492_testPhoneMustNotBeNull() {
    MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    impleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone(null);
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}


        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }
//-----
//-------------------------------------------phone pattern--------------------------------------------//
@Test
    void B5905492_testPhoneMustNotLessThan10() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0123456");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}
        

        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }
    //-------------------------------------------phone pattern--------------------------------------------//
@Test
    void B5905492_testPhoneMustNotMoreThan10() {
        MedicalStaff medicalStaff = new MedicalStaff();
        Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");


	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("01234567891");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}
        

        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }
    //-------------------------------------------phone pattern--------------------------------------------//
 @Test
    void  B5905492_testPhonePatternfail() {
       MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	    Gender gender = genderRepository.findById(1);
   	    Province province = provinceRepository.findById(1);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

	    medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("123456789A");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("1997/05/04");
            medicalStaff.setBirthdate(date) ;
        } catch (ParseException e) {}


        try {
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());// result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }
//-------------------------------------------birthdate Pattern--------------------------------------------//
    @Test
    void B5905492_testBirthdatePatternFail() {
       	 MedicalStaff medicalStaff = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	 Gender gender = genderRepository.findById(1);
   	 Province province = provinceRepository.findById(1);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        medicalStaff.setFullname("Sasithon Chairat");
        medicalStaff.setPhone("0987458748");
        medicalStaff.setGender(gender);
        medicalStaff.setPosition(position);
	    medicalStaff.setProvince(province);
        medicalStaff.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff.setPassword("12345678");
        medicalStaff.setEmail("sasithon@gmail.com");
        try {
            Date date = formatter.parse("2563-01-21");
            medicalStaff.setBirthdate(date);
            
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());

            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must match \"yyyy/MM/dd\"", v.getMessage());
            assertEquals("date", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }
//-------------------------------------------email unique--------------------------------------------//

    @Test
    void B5905492_testEmailMustBeUnique() {
       MedicalStaff medicalStaff1 = new MedicalStaff();
         Position position = positionRepository.findById(1);
   	 Gender gender = genderRepository.findById(1);
   	 Province province = provinceRepository.findById(1);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        try {
        medicalStaff1.setFullname("Sasithon Chairat");
        medicalStaff1.setPhone("0987458748");
        medicalStaff1.setGender(gender);
	    Date date = formatter.parse("1997-01-11");
	    medicalStaff1.setBirthdate(date);
        medicalStaff1.setPosition(position);
	    medicalStaff1.setProvince(province);
        medicalStaff1.setAddress("97/3 หนองหาน อุดรธานี 41130");
        medicalStaff1.setPassword("12345678");
	    medicalStaff1.setEmail("sasithon@gmail.com");
        medicalStaff1 =  medicalStaffRepository.save( medicalStaff1);
            
        } catch (DataIntegrityViolationException e) {
        assertThrows(DataIntegrityViolationException.class, () ->{
           	 MedicalStaff medicalStaff2 = new MedicalStaff();
		
		    medicalStaff2.setFullname("Sasithon Chairat");
		    medicalStaff2.setEmail("sasithon@gmail.com");
                medicalStaff2.setGender(gender);

            try{
		Date date2 = formatter.parse("1997/01/11");
                medicalStaff2.setBirthdate(date2);
            } catch (ParseException ex) {}    
		
		    medicalStaff2.setProvince(province);
		    medicalStaff2.setPassword("12345678");
                medicalStaff2.setPosition(position);
                medicalStaff2.setPhone("0987458748");
                medicalStaff2.setAddress("97/3 หนองหาน อุดรธานี 41130");
  
                medicalStaff2 = medicalStaffRepository.save(medicalStaff2);
            
        });
        } catch (ParseException e) {} 
    }
    //-------------------------------------------gender notnull--------------------------------------------//
@Test
    void B5905492_testGenderComboboxMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff.setBirthdate(date);
            medicalStaff.setGender(null);
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("gender", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
//-------------------------------------------position notnull--------------------------------------------//
@Test
    void B5905492_testPositionComboboxMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff.setBirthdate(date);
            medicalStaff.setPosition(null);
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("position", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
//-------------------------------------------province notnull--------------------------------------------//
@Test
    void B5905492_testProvinceComboboxMustNotBeNull() {
        MedicalStaff medicalStaff = new MedicalStaff();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = formatter.parse("2563/01/21");
            medicalStaff.setBirthdate(date);
            medicalStaff.setProvince(null);
            medicalStaff = medicalStaffRepository.save(medicalStaff);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<MedicalStaff>> result = validator.validate(medicalStaff);
            assertEquals(1, result.size());
    
            ConstraintViolation<MedicalStaff> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("province", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
}
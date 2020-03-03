package com.sut.se.G10;

import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Register.Repository.GenderRepository;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;

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
public class PatientTests {

    private Validator validator;

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    
    @Test
    void B5910557_testSavePatientCorrect() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        patient = patientRepository.saveAndFlush(patient);
        Optional<Patient> found = patientRepository.findById(patient.getId());
        assertEquals("Wachiraya Chaiyasaj", found.get().getPatientfullname());
        assertEquals("1234567890159", found.get().getPersonId());
        assertEquals(gender, found.get().getGender());
        assertEquals(bloodtype, found.get().getBloodtype());
        assertEquals("0982208997", found.get().getPhone());
        assertEquals("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190", found.get().getAddress());
        assertEquals(new Date(1987, 6, 5), found.get().getPatientdate());
        try {
            assertEquals(formatter.parse("1997-09-21"), found.get().getPatientbirthdate());
        } catch (ParseException e) {}
    }
    
    //---------------------------------TEST BIRTHDATE--------------------------------
    @Test
    void B5910557_testPatientBirthdateMustNotBeNull() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);

        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        patient.setPatientbirthdate(null);
        patient.setPatientdate(new Date(1987, 6, 5));
        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {

            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("patientbirthdate", v.getPropertyPath().toString());
        }
    }

    @Test
    void B5910557_testPatientBirthdatePatternFail() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        patient.setPatientdate(new Date(1987, 6, 5));
        try {
            Date date = formatter.parse("1987-01-21");
            patient.setPatientbirthdate(date);
            
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());

            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"yyyy-MM-dd\"", v.getMessage());
            assertEquals("patientbirthdate", v.getPropertyPath().toString());
        } catch (ParseException e) {} 
    }

    //---------------------------------TEST NOT NULL--------------------------------
    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testNameMustNotBeNull() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        patient.setPatientfullname(null);
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("patientfullname", v.getPropertyPath().toString());
        }
    }
    
    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testPersonIDMustNotBeNull() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
		patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId(null);
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("personId", v.getPropertyPath().toString());
        }
    }

    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testPhoneMustNotBeNull() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone(null);
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }

    //ใส่ข้อมูลไม่ถูกต้องมีค่าnull
    @Test
	void B5910557_testAddressMustNotBeNull() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress(null);
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("address", v.getPropertyPath().toString());
        }
    }
    //PatientDate ต้องnot null
    @Test
    void B5910557_testPatientdateMustNotBeNull() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(null);

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("patientdate", v.getPropertyPath().toString());
        }
    }
    //----------------------------------END MUST NOT BE NULL---------------------------------

    //-------------------------------PATIENT DATE PATTERN--------------------------------
    @Test
    void b6020712_testPatientDatePatternFail() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        patient.setPatientdate(new Date(1987, 6, 5));
        try {
            Date date = formatter.parse("1987-06-21");
            patient.setPatientdate(date);

            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());

            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"yyyy-MM-dd\"", v.getMessage());
            assertEquals("patientdate", v.getPropertyPath().toString());
        } catch (ParseException e) {
        }
    }
    //-------------------------------END PATIENT DATE PATTERN----------------------------

    //-------------------------------------PHONE---------------------------------------------
    //---------------------------------TEST PATTERN--------------------------------
    //ใส่ข้อมูลไม่ถูกต้องไม่ถูกpattern
    @Test
    void  B5910557_testPhonePatternfial() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("123456789A");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());// result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }

    //---------------------------PHONE LESS/MORE 10---------------------------
    @Test
	void B5910557_testPhoneMustNotLessThan10() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0123456");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }
    @Test
	void B5910557_testPhoneMustNotMoreThan10() {
        Patient patient = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("01234567891");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{10}\"", v.getMessage());
            assertEquals("phone", v.getPropertyPath().toString());
        }
    }
    //-------------------------END PHONE LESS/MORE 10---------------------------
    //---------------------------------END PHONE-------------------------------------

    //------------------------------PATIENTFULLNAME----------------------------------
    //---------------------------------TEST SIZE--------------------------------
    //ใส่ข้อมูลไม่ถูกต้องไม่ถูกตามsizeที่กำหนด
    @Test
    void  B5910557_testPatientFullnameSizeLessThan10() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        
        patient.setPatientfullname("Wachiraya");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());// result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must be betweet 10 to 50", v.getMessage());
            assertEquals("patientfullname", v.getPropertyPath().toString());
        }
    }

    @Test
    void  B5910557_testPatientFullnameSizeMoreThan50() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        
        patient.setPatientfullname("Wachiraya Chaiyasajjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
        patient.setPersonId("1234567890159");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());// result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must be betweet 10 to 50", v.getMessage());
            assertEquals("patientfullname", v.getPropertyPath().toString());
        }
    }
    //-----------------------------END TEST SIZE----------------------------------
    //----------------------------PATIENTFULLNAME---------------------------------

    //--------------------------------PERSON ID-----------------------------------
    //---------------------------PERSON ID LESS/MORE 13---------------------------
    @Test
	void B5910557_testPersonIDMustNotLessThen13() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("123456789012");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{13}\"", v.getMessage());
            assertEquals("personId", v.getPropertyPath().toString());
        }
    }

    @Test
	void B5910557_testPersonIDMustNotMoreThen13() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        
        patient.setPatientfullname("Wachiraya Chaiyasaj");
        patient.setPersonId("12345678901234");
        patient.setGender(gender);
        patient.setBloodtype(bloodtype);
		patient.setPhone("0982208997");
        patient.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
        try {
            Date Patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(Patientbirthdate) ;
        } catch (ParseException e) {}
        patient.setPatientdate(new Date(1987, 6, 5));

        try {
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size()); // result ต้องมี error 1 ค่าเท่านั้น
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must match \"\\d{13}\"", v.getMessage());
            assertEquals("personId", v.getPropertyPath().toString());
        }
    }
    //-------------------------END PERSON ID LESS/MORE 13---------------------------

    //----------------------------Person ID Be Unique-------------------------------
    @Test
    void B5910557_testPersonIdMustBeUnique() {
        Patient patient1 = new Patient();
        Gender gender = genderRepository.findById(1);
        Bloodtype bloodtype = bloodtypeRepository.findById(1);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            patient1.setPatientfullname("Wachiraya Chaiyasaj");
            patient1.setPersonId("1234567890159");
            patient1.setGender(gender);
            Date date = formatter.parse("1997/01/11");
            patient1.setPatientbirthdate(date);
            patient1.setBloodtype(bloodtype);
            patient1.setPhone("0982208997");
            patient1.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
            patient1.setPatientdate(new Date());
            patient1 = patientRepository.save(patient1);
            
        } catch (DataIntegrityViolationException e) {
        assertThrows(DataIntegrityViolationException.class, () ->{
            Patient patient2 = new Patient();

            
                patient2.setPatientfullname("Wachiraya Chaiyasaj");
                patient2.setPersonId("1234567890159");
                patient2.setGender(gender);

            try{
                Date date2 = formatter.parse("1997/01/11");
                patient2.setPatientbirthdate(date2);
            } catch (ParseException ex) {}    

                patient2.setBloodtype(bloodtype);
                patient2.setPhone("0982208997");
                patient2.setAddress("14/2 ม.4 ต.กระโทก อ.โชคชัย จ.นครรราชสีมา 30190");
                patient2.setPatientdate(new Date());
                patient2 = patientRepository.save(patient2);
            
        });
        } catch (ParseException e) {} 
    }
    //---------------------------------END PERSON ID------------------------------------


    //---------------------------------TEST COMBOBOX--------------------------------
    // ---------------- Gender Combobox ----------------
    @Test
    void B5910557_testGenserComboboxMustNotBeNull() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563-01-21");
            patient.setPatientbirthdate(date);
            patient.setGender(null);
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("gender", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
    
    // ---------------- Bloodtype Combobox ----------------
    @Test
    void B5910557_testBloodtypeComboboxMustNotBeNull() {
        Patient patient = new Patient();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse("2563-01-21");
            patient.setPatientbirthdate(date);
            patient.setBloodtype(null);
            patient = patientRepository.save(patient);
        } catch (ConstraintViolationException e) {
            Set<ConstraintViolation<Patient>> result = validator.validate(patient);
            assertEquals(1, result.size());
    
            ConstraintViolation<Patient> v = result.iterator().next();
            assertEquals("must not be null", v.getMessage());
            assertEquals("bloodtype", v.getPropertyPath().toString());
        } catch (ParseException e) {}
    }
    //---------------------------------END COMBOBOX--------------------------------

}
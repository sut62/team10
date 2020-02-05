package com.sut.se.G10;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.sut.se.G10.Diagnose.Entity.Diagnose;
import com.sut.se.G10.Diagnose.Repository.DiagnoseRepository;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Diagnose.Entity.Admission;
import com.sut.se.G10.Diagnose.Repository.AdmissionRepository;
import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.ProvinceRepository;
import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Register.Repository.GenderRepository;
import com.sut.se.G10.Register.Entity.Position;
import com.sut.se.G10.Register.Repository.PositionRepository;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;
import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;
import com.sut.se.G10.Diagnose.Repository.BloodPressureLevelRepository;

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

import java.util.Optional;
import java.util.Set;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

@DataJpaTest
public class DiagnoseTests {

    private Validator validator;

    @Autowired
    private DiagnoseRepository diagnoseRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private AdmissionRepository admissionRepository;
    @Autowired
    private MedicalStaffRepository medicalStaffRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private BloodPressureLevelRepository bloodPressureLevelRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private GenderRepository genderRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private BloodtypeRepository bloodtypeRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    // ---------------------------------- Test All Field ---------------------------------------------
    @Test
    void b5911837_testDiagnoseAllCorrect() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("ABab12 _.,");
        diagnose.setDiagnosisDate(new Date(2020, 12, 25));
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        diagnose =  diagnoseRepository.saveAndFlush(diagnose);

        Optional<Diagnose> found = diagnoseRepository.findById(diagnose.getId());
        assertEquals("ABab12 _.,", found.get().getDiagnosis());
        assertEquals(new Date(2020, 12, 25), found.get().getDiagnosisDate());
        assertEquals(333L, found.get().getStayAlertedTime());
        assertEquals(bloodPressureLevelRepository.findById(1), found.get().getBloodPressureLevel());
        assertEquals(admissionRepository.findById(1), found.get().getAdmission());
        assertEquals(patient, found.get().getPatient());
        assertEquals(medicalStaff, found.get().getDiagnosisDoctor());
    }

    // ---------------------------------- Test String diagnoseCode Field ---------------------------------------------
    @Test
    void b5911837_testDiagnoseCodeNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode(null);
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnoseCode", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnoseCodeMustBeUnique() {
        Diagnose diagnose1 = new  Diagnose();
        diagnose1.setDiagnosis("1234567890");
        diagnose1.setDiagnosisDate(new Date());
        diagnose1.setStayAlertedTime(333L);
        diagnose1.setDiagnoseCode("XXXXX-XXXXX");
        diagnose1.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose1.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose1.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose1.setPatient(patient);

        diagnoseRepository.saveAndFlush(diagnose1);

        // คาดหวังว่า DataIntegrityViolationException จะถูก throw
        assertThrows(DataIntegrityViolationException.class, () -> {
            Diagnose diagnose2 = new  Diagnose();
            diagnose2.setDiagnosis("1234567890");
            diagnose2.setDiagnosisDate(new Date());
            diagnose2.setStayAlertedTime(333L);
            diagnose2.setDiagnoseCode("XXXXX-XXXXX");
            diagnose2.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
            diagnose2.setAdmission(admissionRepository.findById(1));
            diagnose2.setDiagnosisDoctor(medicalStaff);

            diagnose2.setPatient(patient);

            diagnoseRepository.saveAndFlush(diagnose2);
        });
    }

    // ---------------------------------- Test Date diagnosisDate Field ---------------------------------------------
    @Test
    void b5911837_testDiagnosisDateNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(null);
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnosisDate", v.getPropertyPath().toString());
    }

    // ---------------------------------- Test String diagnosis Field ---------------------------------------------
    @Test
    void b5911837_testDiagnosisNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis(null);
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisNotAgianstPattern() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("123456789$");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must match \"[a-zA-Z0-9._, \t]*\"", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisMin10() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("123456789");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 10 and 100", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testDiagnosisMax100() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("12345678901234567890123456789012345678901234567890" 
        + "123456789012345678901234567890123456789012345678901");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("size must be between 10 and 100", v.getMessage());
        assertEquals("diagnosis", v.getPropertyPath().toString());
    }

    // ---------------------------------- Test int stayAleartedTime Field ---------------------------------------------
    @Test
    void b5911837_testStayAlertedTimeMustNotBeNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(null);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testStayAlertedTimeNumMin0() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(-1L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

    @Test
    void b5911837_testStayAlertedTimeNumMax99999() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(100000L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must be less than or equal to 99999", v.getMessage());
        assertEquals("stayAlertedTime", v.getPropertyPath().toString());
    }

    // ---------------------------------- Test Patient patient Field ---------------------------------------------
    @Test
    void b5911837_testPatientNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        diagnose.setPatient(null);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("patient", v.getPropertyPath().toString());
    }

    // ---------------------------------- Test MedicalStaff diagnosisDoctor Field ---------------------------------------------
    @Test
    void b5911837_testMedicalStaffNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(admissionRepository.findById(1));

        diagnose.setDiagnosisDoctor(null);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("diagnosisDoctor", v.getPropertyPath().toString());
    }

    // ---------------------------------- Test Admission admission Field ---------------------------------------------
    @Test
    void b5911837_testAdmissionNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(bloodPressureLevelRepository.findById(1));
        diagnose.setAdmission(null);
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("admission", v.getPropertyPath().toString());
    }

    // ---------------------------------- Test BloodPressureLevel bloodPressureLevel Field ---------------------------------------------
    @Test
    void b5911837_testBloodPressureLevelNotNull() {
        Diagnose diagnose = new  Diagnose();
        diagnose.setDiagnosis("1234567890");
        diagnose.setDiagnosisDate(new Date());
        diagnose.setStayAlertedTime(333L);
        diagnose.setDiagnoseCode("XXXXX-XXXXX");
        diagnose.setBloodPressureLevel(null);
        diagnose.setAdmission(admissionRepository.findById(1));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		MedicalStaff medicalStaff = new MedicalStaff();
		medicalStaff.setFullname("abced Doctor");
        medicalStaff.setAddress("1111111111111111111");
        try{
            Date medicalstaffbirthdate = formatter.parse("1998-04-21");
            medicalStaff.setBirthdate(medicalstaffbirthdate);
        } catch(ParseException e){}
		medicalStaff.setEmail("a@gmail.com");
		medicalStaff.setPassword("12345678");
		medicalStaff.setPhone("1234567890");
		medicalStaff.setPosition(positionRepository.findByPosition("Doctor"));
		medicalStaff.setGender(genderRepository.findById(1));
		medicalStaff.setProvince(provinceRepository.findById(1));
        medicalStaffRepository.save(medicalStaff);
        diagnose.setDiagnosisDoctor(medicalStaff);

        Patient patient = new Patient();
		patient.setPatientfullname("Nawapat  Sue");
		patient.setPersonId("1234567890123");
		patient.setPhone("1234567890");
        patient.setAddress("1111111111111111111");
        try{
            Date patientbirthdate = formatter.parse("1997-09-21");
            patient.setPatientbirthdate(patientbirthdate);
        } catch(ParseException e){}
		patient.setPatientdate(new Date());
		patient.setBloodtype(bloodtypeRepository.findById(1));
		patient.setGender(genderRepository.findById(1));
		patientRepository.save(patient);
        diagnose.setPatient(patient);

        Set<ConstraintViolation<Diagnose>> result = validator.validate(diagnose);

        // result ต้องมี error 1 ค่าเท่านั้น
        assertEquals(1, result.size());

        // error message ตรงชนิด และถูก field
        ConstraintViolation<Diagnose> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bloodPressureLevel", v.getPropertyPath().toString());
    }

}
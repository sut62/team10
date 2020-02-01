package com.sut.se.G10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.stream.Stream;
import java.util.Date;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Entity.Rate;
import com.sut.se.G10.Contagion.Entity.Symptom;
import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Contagion.Entity.Heal;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Contagion.Repository.HealRepository;
import com.sut.se.G10.Contagion.Repository.RateRepository;
import com.sut.se.G10.Contagion.Repository.SymptomRepository;
import com.sut.se.G10.Contagion.Repository.TypeRepository;
import com.sut.se.G10.Diagnose.Entity.Admission;
import com.sut.se.G10.Diagnose.Repository.AdmissionRepository;
import com.sut.se.G10.Patient.Entity.Bloodtype;
import com.sut.se.G10.Patient.Repository.BloodtypeRepository;
import com.sut.se.G10.Register.Entity.Gender;
import com.sut.se.G10.Register.Entity.Position;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.GenderRepository;
import com.sut.se.G10.Register.Repository.PositionRepository;
import com.sut.se.G10.Register.Repository.ProvinceRepository;
import com.sut.se.G10.Riskarea.Entity.Communicablelevel;
import com.sut.se.G10.Riskarea.Repository.CommunicablelevelRepository;
import com.sut.se.G10.VaccineInformation.Entity.TypeVaccine;
import com.sut.se.G10.VaccineInformation.Entity.Vaccine;
import com.sut.se.G10.VaccineInformation.Repository.TypeVaccineRepository;
import com.sut.se.G10.VaccineInformation.Repository.VaccineRepository;
import com.sut.se.G10.Register.Entity.MedicalStaff;
import com.sut.se.G10.Register.Repository.MedicalStaffRepository;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Patient.Repository.PatientRepository;
import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;
import com.sut.se.G10.Diagnose.Repository.BloodPressureLevelRepository;

@SpringBootApplication
public class G10Application {

	public static void main(String[] args) {
		SpringApplication.run(G10Application.class, args);
	}

	@Bean
	ApplicationRunner init( DiseaseRepository diseaseRepository,
							TypeRepository typeRepository,
							SymptomRepository symptomRepository,
							RateRepository rateRepository, 
							HealRepository healRepository,
							AdmissionRepository admissionRepository,
							BloodtypeRepository bloodtypeRepository,
							ProvinceRepository provinceRepository,
							GenderRepository genderRepository,
							PositionRepository positionRepository,
							CommunicablelevelRepository communicablelevelRepository,
							TypeVaccineRepository typeVaccineRepository,
							VaccineRepository vaccineRepository,
							PatientRepository patientRepository,
							MedicalStaffRepository medicalStaffRepository,
							BloodPressureLevelRepository bloodPressureLevelRepository ) {

		return args -> {
			// Contagion Part
			Stream.of("ไม่มีโรค", "ตาแดง","ไข้ฉี่หนู","โรคอุจจาระร่วงเฉียบพลัน",
					"บิด","ไทฟอยด์","อาหารเป็นพิษ",
					"หวัด","ไข้หวัดใหญ่","ปอดบวม",
					"ไข้หวัดนก","ไข้เลือดออกไข้สมองอักเสบเจอี","ไข้มาลาเลีย",
					"เยื่อบุตาอักเสบ","โรคธาลัสซีเมีย","โรคเอดส์").forEach(newdisease -> {
				Disease disease = new Disease();
				disease.setDisease(newdisease);
				diseaseRepository.save(disease);
			});
			Stream.of("กลุ่มเชื้อโรคที่ผ่านทางบาดแผลหรือเยื่อบุผิวหนัง",
					"กลุ่มโรคติดต่อทางน้ำและอาหาร",
					"กลุ่มโรคระบาดทางเดินหายใจ",
					"กลุ่มโรคที่มีพาหะ",
					"โรคติดต่อทางพันธุกรรม",
					"โรคติดต่อทางเพศสัมพันธ์").forEach(newtype -> {
				Type type = new Type(); 
				type.setType(newtype); 
				typeRepository.save(type); 
			});
			Stream.of("รุนเเรง","ไม่รุนเเรง").forEach(newsymptom -> {
				Symptom symptom = new Symptom(); 
				symptom.setSymptom(newsymptom); 
				symptomRepository.save(symptom); 
			});			
			Stream.of("ติดต่อได้ง่าย","ไม่ติดต่อ").forEach(newrate -> {
				Rate rate = new Rate();
				rate.setRate(newrate);
				rateRepository.save(rate);
			});
			Stream.of("ให้ยา","ให้วัคซีน","ไม่มี").forEach(newrate -> {
				Heal heal = new Heal();
				heal.setHeal(newrate);
				healRepository.save(heal);
			});
			//Statistics = Disease,Type,Province
			
			// Diagnose Part
			Stream.of("Admitted", "Not admitted", "Uncertainly").forEach(admitted -> {
				Admission admission = new Admission();
				admission.setAdmitted(admitted);
				admissionRepository.save(admission);
			});

			// Patient Part
			Stream.of(	"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-").forEach(newbloodtype -> {
				Bloodtype bloodtype = new Bloodtype();
				bloodtype.setBloodtype(newbloodtype); 
				bloodtypeRepository.save(bloodtype);
			});
			
			// Register Part
			Stream.of("กรุงเทพมหานคร", "กระบี่", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี", "ฉะเชิงเทรา",
                "ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด", "ตาก", "นครนายก",
                "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี", "นราธิวาส", "น่าน",
                "หนองคาย", "หนองบัวลำภู", "บึงกาฬ", "บุรีรัมย์", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี",
                "พระนครศรีอยุธยา", "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่", "พะเยา",
                "ภูเก็ต", "มหาสารคาม", "แม่ฮ่องสอน", "มุกดาหาร", "ยะลา", "ยโสธร", "ร้อยเอ็ด", "ระนอง", "ระยอง",
                "ราชบุรี", "ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล", "สมุทรปราการ",
                "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี", "สุราษฎร์ธานี",
                "สุรินทร์", "อ่างทอง", "อุดรธานี", "อุทัยธานี", "อุตรดิตถ์", "อุบลราชธานี", "อำนาจเจริญ").forEach(place -> {
                    Province province = new Province();
                    province.setProvince(place);
                    provinceRepository.save(province);
				});
					
			Stream.of("ชาย", "หญิง").forEach(sexual -> {
					Gender gender = new Gender();
					gender.setGender(sexual);
					genderRepository.save(gender);
				});	

			Stream.of("Doctor", "Nurse","Pharmacy").forEach(newPosition -> {
					Position position = new Position();
					position.setPosition(newPosition);
					positionRepository.save(position);
				});

			// Riskarea Part
			Stream.of("ระบาดเล็กน้อย (น้อยกว่า 10 คน)", 
					  "ระบาดปานกลาง (ระหว่าง 10 - 30 คน)",
					  "ระบาดระดับเฝ้าะวัง (ระหว่าง 30 - 50 คน)",
					  "ระบาดรุนแรง (ตั้งแต่ 51 คนขึ้นไป)").forEach(newCommunicablelevel -> {
				Communicablelevel communicablelevel = new Communicablelevel() ;
				communicablelevel.setCommunicablelevel(newCommunicablelevel) ;
				communicablelevelRepository.save(communicablelevel) ;
			});

			// VaccineInfomation Part
			Object[][] user = new Object[][] { 
				{ "วัคซีนป้องกันไข้หวัดใหญ่","อาการบริเวณที่ฉีด เจ็บบริเวณที่ฉีดซึ่งไม่มาก และจะหายใน 2 วัน  อาการทั่วๆไปจะมีไข้ ปวดตามตัวหลังจากฉีด 6-12 ชั่วโมงและอยู่ได้นาน 1-2 วัน บางรายอาจจะมีผื่นลมพิษริมฝีปากบวม"},
				{ "วัคซีนป้องกันไข้สุกใสไข้สุกใส", "โดยทั่วไปไม่ค่อยมีโรคแทรกซ้อนอาจจะมีปอดบวมหรือติดเชื้อที่ผิวหนัง ถ้าเชื้อนี้เกิดในผู้ใหญ่ผื่นจะเกิดมากและไข้จะอยู่นานและเกิดโรคแทรกซ้อนปอดบวมได้สูง หากติดเชื้อนี้ขณะตั้งครรภ์อาจทำให้เด็กเกิดมาพิการได้"},
				{ "วัคซีนป้องกันพิษสุนัขบ้า", "ผู้ที่ได้รับวัคซีนอาจมีอาการปวด บวม แดง และคันบริเวณที่ฉีด"} };

			for (int i = 0; i < user.length; i++) {
				Vaccine vaccine = new Vaccine();

				vaccine.setVaccinename((String) user[i][0]);
				vaccine.setVaccinedata((String) user[i][1]);
				vaccineRepository.save(vaccine);
			}

			Stream.of("ยาใช้ภายนอก", "ยาใช้ภายใน").forEach(list -> {
				TypeVaccine typevaccine = new TypeVaccine(); 
				typevaccine.setTypevaccinelist(list);
				typeVaccineRepository.save(typevaccine); 
			});

			Stream.of("Very high", "High", "Normal", "Low", "Very low").forEach(level -> {
				BloodPressureLevel bloodPressureLevel = new BloodPressureLevel(); 
				bloodPressureLevel.setLevel(level);
				bloodPressureLevelRepository.save(bloodPressureLevel); 
			});

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;

			MedicalStaff medicalStaff1 = new MedicalStaff();
			medicalStaff1.setFullname("abced Doctor");
			medicalStaff1.setAddress("1111111111111111111");
            Date medicalstaffbirthdate1 = formatter.parse("1998-04-21");
            medicalStaff1.setBirthdate(medicalstaffbirthdate1);
			medicalStaff1.setEmail("a@gmail.com");
			medicalStaff1.setPassword("12345678");
			medicalStaff1.setPhone("1234567890");
			medicalStaff1.setPosition(positionRepository.findByPosition("Doctor"));
			medicalStaff1.setGender(genderRepository.findById(1));
			medicalStaff1.setProvince(provinceRepository.findById(1));
			medicalStaffRepository.save(medicalStaff1);

			MedicalStaff medicalStaff2 = new MedicalStaff();
			medicalStaff2.setFullname("abced Nurse");
			medicalStaff2.setAddress("1111111111111111111");
            Date medicalstaffbirthdate2 = formatter.parse("1998-05-21");
            medicalStaff2.setBirthdate(medicalstaffbirthdate2);
			medicalStaff2.setEmail("b@gmail.com");
			medicalStaff2.setPassword("12345678");
			medicalStaff2.setPhone("1234567890");
			medicalStaff2.setPosition(positionRepository.findByPosition("Nurse"));
			medicalStaff2.setGender(genderRepository.findById(1));
			medicalStaff2.setProvince(provinceRepository.findById(1));
			medicalStaffRepository.save(medicalStaff2);


			Patient patient1 = new Patient();
			patient1.setPatientfullname("Nawapat  Sue");
			patient1.setPersonId("1234567890123");
			patient1.setPhone("1234567890");
			patient1.setAddress("1111111111111111111");
            Date patientbirthdate1 = formatter.parse("1997-09-21");
            patient1.setPatientbirthdate(patientbirthdate1) ;
			patient1.setPatientdate(new Date());
			patient1.setBloodtype(bloodtypeRepository.findById(1));
			patient1.setGender(genderRepository.findById(1));
			patient1.setDisease(diseaseRepository.findById(1));
			patientRepository.save(patient1);


			diseaseRepository.findAll().forEach(System.out::println);
			typeRepository.findAll().forEach(System.out::println);
			symptomRepository.findAll().forEach(System.out::println);
			rateRepository.findAll().forEach(System.out::println);
			admissionRepository.findAll().forEach(System.out::println);
			bloodtypeRepository.findAll().forEach(System.out::println);
			genderRepository.findAll().forEach(System.out::println);
			provinceRepository.findAll().forEach(System.out::println);
			positionRepository.findAll().forEach(System.out::println);
			communicablelevelRepository.findAll().forEach(System.out::println);
			vaccineRepository.findAll().forEach(System.out::println); 
			typeVaccineRepository.findAll().forEach(System.out::println);
			bloodPressureLevelRepository.findAll().forEach(System.out::println);
			patientRepository.findAll().forEach(System.out::println); 
			medicalStaffRepository.findAll().forEach(System.out::println);
		};
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}

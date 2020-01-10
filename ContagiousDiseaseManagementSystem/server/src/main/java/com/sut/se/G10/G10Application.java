package com.sut.se.G10;

import java.util.Collections;
import java.util.stream.Stream;

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
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
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
							AdmissionRepository admissionRepository,
							BloodtypeRepository bloodtypeRepository,
							ProvinceRepository provinceRepository,
							GenderRepository genderRepository,
							PositionRepository positionRepository,
							CommunicablelevelRepository communicablelevelRepository,
							TypeVaccineRepository typeVaccineRepository,
							VaccineRepository vaccineRepository ) {

		return args -> {
			// Contagion Part
			Stream.of("ตาแดง","ไข้ฉี่หนู","โรคอุจจาระร่วงเฉียบพลัน",
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
			Stream.of("พร้อมใช้งาน", "กำลังใช้งาน", "ทั้งหมด..").forEach(name -> {
				Vaccine vaccine = new Vaccine(); 
				vaccine.setVaccinename(name); 
				vaccineRepository.save(vaccine); 
			});

			Stream.of("ยาใช้ภายนอก", "ยาใช้ภายใน").forEach(list -> {
				TypeVaccine typevaccine = new TypeVaccine(); 
				typevaccine.setTypevaccinelist(list);
				typeVaccineRepository.save(typevaccine); 
			});

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

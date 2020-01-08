package com.sut.se.G10;

import com.sut.se.G10.Entity.Contagion;
import com.sut.se.G10.Entity.Disease;
import com.sut.se.G10.Entity.Rate;
import com.sut.se.G10.Entity.Symptom;
import com.sut.se.G10.Entity.Type;
import com.sut.se.G10.Repository.ContagionRepository;
import com.sut.se.G10.Repository.DiseaseRepository;
import com.sut.se.G10.Repository.RateRepository;
import com.sut.se.G10.Repository.SymptomRepository;
import com.sut.se.G10.Repository.TypeRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringBootVueApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootVueApplication.class, args);
	}

	@Bean
	ApplicationRunner init(DiseaseRepository diseaseRepository,TypeRepository typeRepository,
						SymptomRepository symptomRepository,RateRepository rateRepository){
							return args -> {
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

								rateRepository.findAll().forEach(System.out::println);
							};
	}
};					

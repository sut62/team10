package com.sut.se.G10.VaccineInformation;

import com.sut.se.G10.VaccineInformation.Entity.*;
import com.sut.se.G10.VaccineInformation.Repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class VaccineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaccineApplication.class, args);
	}
	@Bean
	ApplicationRunner init(MedicalStaffRepository medicalStaffRepository,VaccineInformationRepository vaccineInformationRepository,
							VaccineRepository vaccineRepository,TypeVaccineRepository typeVaccineRepository) {
		return args -> {

			Stream.of("ตั๊ก", "ชมพู่", "แบงค์", "ตาล", "เบน", "หนุน").forEach(fullname -> {
				MedicalStaff medicalStaff = new MedicalStaff(); 
				medicalStaff.setFullname(fullname); 
				medicalStaffRepository.save(medicalStaff); 
			});
		

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

			medicalStaffRepository.findAll().forEach(System.out::println);
			vaccineRepository.findAll().forEach(System.out::println); 
			typeVaccineRepository.findAll().forEach(System.out::println);
			


		};
	}

}

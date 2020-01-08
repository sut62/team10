package com.communicablediseasemanagement.patient;

import com.communicablediseasemanagement.patient.entity.*;
import com.communicablediseasemanagement.patient.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class G10Application {

	public static void main(String[] args) {
		SpringApplication.run(G10Application.class, args);
	}

	@Bean
    ApplicationRunner init( Diseaserepository diseaserepository,
							Bloodtyperepository bloodtyperepository,
							Genderrepository genderrepository) {
		return args -> {
			Stream.of("โรคไข้เหลือง", "โรคเอดส์", "โรคไข้เลือดออก", "โรคไก่ทอด").forEach(newdisease -> {
				Disease disease = new Disease(); 
				disease.setDisease(newdisease); 
				diseaserepository.save(disease); 
			});
			
			Stream.of(	"A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-").forEach(newbloodtype -> {
				Bloodtype bloodtype = new Bloodtype();
				bloodtype.setBloodtype(newbloodtype); 
				bloodtyperepository.save(bloodtype); 
			});

			Stream.of("ชาย", "หญิง").forEach(newgender -> {
				Gender gender = new Gender();
				gender.setGender(newgender); 
				genderrepository.save(gender); 
			});

			diseaserepository.findAll().forEach(System.out::println);
			bloodtyperepository.findAll().forEach(System.out::println); 
			genderrepository.findAll().forEach(System.out::println); 

		};
	}
}
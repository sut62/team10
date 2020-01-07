package com.sut.se.G10;

import java.util.stream.Stream;

import com.sut.se.G10.entity.Communicablelevel;
import com.sut.se.G10.repository.Communicablelevelrepository;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class G10Application {

	public static void main(String[] args) {
		SpringApplication.run(G10Application.class, args);
	}

	@Bean
	ApplicationRunner init( Communicablelevelrepository communicablelevelrepository ) {
		return args -> {
			Stream.of("ระบาดเล็กน้อย (น้อยกว่า 10 คน)", 
					  "ระบาดปานกลาง (ระหว่าง 10 - 30 คน)",
					  "ระบาดระดับเฝ้าะวัง (ระหว่าง 30 - 50 คน)",
					  "ระบาดรุนแรง (ตั้งแต่ 51 คนขึ้นไป)").forEach(newCommunicablelevel -> {
				Communicablelevel communicablelevel = new Communicablelevel() ;
				communicablelevel.setCommunicablelevel(newCommunicablelevel) ;
				communicablelevelrepository.save(communicablelevel) ;
			});

			communicablelevelrepository.findAll().forEach(System.out::println);
		};
	}
}

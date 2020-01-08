package com.sut.se.G10.Register;

import com.sut.se.G10.Register.entity.*;
import com.sut.se.G10.Register.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

	@Bean
    ApplicationRunner init( MedicalStaffRepository medicalStaffRepository,
							GenderRepository genderRepository,
							PositionRepository  positionRepository,
							ProvinceRepository provinceRepository) {
		return args -> {
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
			genderRepository.findAll().forEach(System.out::println);
			provinceRepository.findAll().forEach(System.out::println);
			positionRepository.findAll().forEach(System.out::println);
			};
		}
}

package com.sut.se.G10.dataloader;

import com.sut.se.G10.entity.MedicalStaff;
import com.sut.se.G10.repository.MedicalStaffRepository;

import java.util.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class MedicalStaffDataLoader implements ApplicationRunner {

    @Autowired private MedicalStaffRepository medicalStaffRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        MedicalStaff med01 = new MedicalStaff();
        Calendar calendar01 = Calendar.getInstance();
        calendar01.set(1958, 3, 27, 0, 0, 0);
        Date date01 = calendar01.getTime();
        med01.setBirthdate(date01);
        med01.setAddress("893 Moo 24 Tumbon Wayi Mueng Pattani");
        med01.setFullname("Somsri Adisak");
        med01.setPhone("0897845672");
        med01.setEmail("somsri_emt@gmail.com");
        med01.setPassword("12345678");
        med01.setPosition("EMT");
        medicalStaffRepository.save(med01);
        
        MedicalStaff med02 = new MedicalStaff();
        Calendar calendar02 = Calendar.getInstance();
        calendar02.set(1979, 0, 31, 0, 0, 0);
        Date date02 = calendar02.getTime();
        med02.setBirthdate(date02);
        med02.setAddress("112 Moo 1 Tumbon Phra Nakhon Mueng Ayothaya");
        med02.setFullname("Warunyoo Sansuk");
        med02.setPhone("0889031857");
        med02.setEmail("warunyoo_med@gmail.com");
        med02.setPassword("12345678");
        med02.setPosition("Doctor");
        medicalStaffRepository.save(med02);

        MedicalStaff med03 = new MedicalStaff();
        Calendar calendar03 = Calendar.getInstance();
        calendar03.set(1989, 2, 4, 0, 0, 0);
        Date date03 = calendar03.getTime();
        med03.setBirthdate(date03);
        med03.setAddress("187 Moo 21 Tumbon Sunkaew Mueng Saraburi");
        med03.setFullname("Somsak Adisak");
        med03.setPhone("0819048599");
        med03.setEmail("somsak_pha@gmail.com");
        med03.setPassword("12345678");
        med03.setPosition("Pharmacist");
        medicalStaffRepository.save(med03);

        MedicalStaff med04 = new MedicalStaff();
        Calendar calendar04 = Calendar.getInstance();
        calendar04.set(1983, 3, 27, 0, 0, 0);
        Date date04 = calendar04.getTime();
        med04.setBirthdate(date04);
        med04.setAddress("109 Moo 10 Tumbon Huachew Mueng Suphanburi");
        med04.setFullname("Rattana Affro");
        med04.setPhone("0947812364");
        med04.setEmail("rattana_med@gmail.com");
        med04.setPassword("12345678");
        med04.setPosition("Doctor");
        medicalStaffRepository.save(med04);
        
        MedicalStaff med05 = new MedicalStaff();
        Calendar calendar05 = Calendar.getInstance();
        calendar05.set(1983, 3, 27, 0, 0, 0);
        Date date05 = calendar05.getTime();
        med05.setBirthdate(date05);
        med05.setAddress("980 Moo 1 Tumbon Wanya Mueng Chiang rai");
        med05.setFullname("Kitsada HRK");
        med05.setPhone("0889031857");
        med05.setEmail("kitsada_nur@gmail.com");
        med05.setPassword("12345678");
        med05.setPosition("Nurse");
        medicalStaffRepository.save(med05);

        MedicalStaff med06 = new MedicalStaff();
        Calendar calendar06 = Calendar.getInstance();
        calendar06.set(1990, 3, 12, 0, 0, 0);
        Date date06 = calendar06.getTime();
        med06.setBirthdate(date06);
        med06.setAddress("902 Moo 4 Tumbon Huakai Mueng Chiang mai");
        med06.setFullname("Araya HRK");
        med06.setPhone("0891249015");
        med06.setEmail("araya_med@gmail.com");
        med06.setPassword("12345678");
        med06.setPosition("Doctor");
        medicalStaffRepository.save(med06);

        medicalStaffRepository.findAll().forEach(System.out::println);
    }

    
}
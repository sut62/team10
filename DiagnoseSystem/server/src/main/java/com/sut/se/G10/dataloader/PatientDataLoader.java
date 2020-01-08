package com.sut.se.G10.dataloader;

import java.util.Date;
import java.util.Calendar;
import java.util.stream.Stream;

import com.sut.se.G10.entity.Patient;
import com.sut.se.G10.repository.PatientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PatientDataLoader implements ApplicationRunner {

    @Autowired private PatientRepository patientRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        Patient pat01 = new Patient();
        Calendar calendar01 = Calendar.getInstance();
        calendar01.set(1998, 3, 20, 0, 0, 0);
        Date date01 = calendar01.getTime();
        pat01.setPatientdate(new Date());
        pat01.setBirthdate(date01);
        pat01.setFullname("Nawapat Theekon");
        pat01.setPhone("0917841876");
        pat01.setAddress("634 Moo 5 Tumbon sunken Mueng Korat");
        patientRepository.save(pat01);
        
        Patient pat02 = new Patient();
        Calendar calendar02 = Calendar.getInstance();
        calendar02.set(1997, 10, 12, 0, 0, 0);
        Date date02 = calendar02.getTime();
        pat02.setPatientdate(new Date());
        pat02.setBirthdate(date02);
        pat02.setFullname("Wachi Sajchi");
        pat02.setPhone("0808927811");
        pat02.setAddress("895 Moo 2 Tumbon Yohai Mueng Chaiyaphum");
        patientRepository.save(pat02);

        Patient pat03 = new Patient();
        Calendar calendar03 = Calendar.getInstance();
        calendar03.set(1999, 0, 1, 0, 0, 0);
        Date date03 = calendar03.getTime();
        pat03.setPatientdate(new Date());
        pat03.setBirthdate(date03);
        pat03.setFullname("Nawapat Theekon");
        pat03.setPhone("0917841876");
        pat03.setAddress("902 Moo 9 Tumbon Toekaew Mueng Prejburi");
        patientRepository.save(pat03);

        patientRepository.findAll().forEach(System.out::println);
    }
    
    
}
package com.sut.se.G10.dataloader;

import java.util.stream.Stream;

import com.sut.se.G10.entity.Admission;
import com.sut.se.G10.repository.AdmissionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AdmissionDataLoader implements ApplicationRunner {

    @Autowired private AdmissionRepository admissionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("Admitted", "Not admitted", "Uncertainly").forEach(admitted -> {
            Admission admission = new Admission();
            admission.setAdmitted(admitted);
            admissionRepository.save(admission);
        });

        admissionRepository.findAll().forEach(System.out::println);
    }

    
}
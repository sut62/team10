package com.sut.se.G10.dataloader;

import java.util.stream.Stream;

import com.sut.se.G10.entity.Disease;
import com.sut.se.G10.repository.DiseaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DiseaseDataLoader implements ApplicationRunner {

    @Autowired private DiseaseRepository diseaseRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Stream.of("CRE", "Ebola", "Enterovirus D68", "Flu", "Hantavirus", "Hepatitis A",
        "Hepatitis B", "HIV/AIDS", "Measles", "MRSA", "Pertussis", "Rabies", "Sexually Transmitted Disease", 
        "Shigellosis", "Tuberculosis", "West Nile Virus", "Zika").forEach(name -> {
            Disease disease = new Disease();
            disease.setName(name);
            diseaseRepository.save(disease);
        });

        diseaseRepository.findAll().forEach(System.out::println);
    }

    
}
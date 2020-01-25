package com.sut.se.G10.Contagion.Controller;

import com.sut.se.G10.Contagion.Entity.Contagion;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Entity.Heal;
import com.sut.se.G10.Contagion.Entity.Rate;
import com.sut.se.G10.Contagion.Entity.Symptom;
import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Contagion.Repository.ContagionRepository;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Contagion.Repository.HealRepository;
import com.sut.se.G10.Contagion.Repository.RateRepository;
import com.sut.se.G10.Contagion.Repository.SymptomRepository;
import com.sut.se.G10.Contagion.Repository.TypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ContagionController {

    @Autowired 
    private final ContagionRepository contagionRepository;
    @Autowired 
    private DiseaseRepository diseaseRepository;
    @Autowired 
    private TypeRepository typeRepository;
    @Autowired 
    private SymptomRepository symptomRepository;
    @Autowired 
    private RateRepository  rateRepository;
    @Autowired 
    private HealRepository healRepository;

    ContagionController(ContagionRepository contagionRepository) {
        this.contagionRepository = contagionRepository;
    }
    
    @GetMapping("/contagion")
    public Collection<Contagion> contagions() {
        return contagionRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/contagion/{disease_id}/{type_id}/{carrier}/{symptom_id}/{rate_id}/{heal_id}")
    public Contagion newContagion(Contagion newContagion,                             
                                    @PathVariable long disease_id,
                                    @PathVariable long type_id, 
                                    @PathVariable String carrier,
                                    @PathVariable long symptom_id,   
                                    @PathVariable long rate_id,
                                    @PathVariable long heal_id){
                                        
        Disease disease = diseaseRepository.findById(disease_id);
        Type type = typeRepository.findById(type_id);
        Symptom symptom = symptomRepository.findById(symptom_id);
        Rate rate = rateRepository.findById(rate_id);
        Heal heal = healRepository.findById(heal_id);

        newContagion.setDisease(disease);
        newContagion.setType(type);
        newContagion.setCarrier(carrier);
        newContagion.setSymptom(symptom);
        newContagion.setRate(rate);
        newContagion.setHeal(heal);
        
        return contagionRepository.save(newContagion);
    }
}


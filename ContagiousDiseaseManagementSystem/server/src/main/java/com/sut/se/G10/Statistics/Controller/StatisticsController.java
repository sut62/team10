package com.sut.se.G10.Statistics.Controller;

import com.sut.se.G10.Statistics.Entity.Statistics;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Statistics.Repository.StatisticsRepository;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Contagion.Repository.TypeRepository;
import com.sut.se.G10.Register.Repository.ProvinceRepository;

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
public class StatisticsController {

    @Autowired 
    private StatisticsRepository statisticsRepository;
    @Autowired 
    private DiseaseRepository diseaseRepository;
    @Autowired 
    private TypeRepository typeRepository;
    @Autowired 
    private ProvinceRepository provinceRepository;

    StatisticsController(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }
    
    @GetMapping("/statistics")
    public Collection<Statistics> statistics() {
        return statisticsRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/statistics/{disease_id}/{type_id}/{province_id}/{rates}")
    public Statistics newContagion(Statistics newStatistics,                             
                                    @PathVariable long disease_id,
                                    @PathVariable long type_id,
                                    @PathVariable long province_id, 
                                    @PathVariable String rates){
                                        
        Disease disease = diseaseRepository.findById(disease_id);
        Type type = typeRepository.findById(type_id);
        Province province = provinceRepository.findById(province_id);

        newStatistics.setDisease(disease);
        newStatistics.setType(type);
        newStatistics.setProvince(province);
        newStatistics.setRates(rates);

        
        return statisticsRepository.save(newStatistics);
    }
}
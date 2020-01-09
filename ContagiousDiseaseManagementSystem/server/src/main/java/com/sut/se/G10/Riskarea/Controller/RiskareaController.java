package com.sut.se.G10.Riskarea.Controller;

import com.sut.se.G10.Riskarea.Repository.RiskareaRepository;
import com.sut.se.G10.Riskarea.Repository.CommunicablelevelRepository;
import com.sut.se.G10.Riskarea.Entity.Riskarea;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Repository.DiseaseRepository;
import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.ProvinceRepository;
import com.sut.se.G10.Riskarea.Entity.Communicablelevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RiskareaController {
    @Autowired
    private final RiskareaRepository riskareaRepository ;
    @Autowired
    private DiseaseRepository diseaseRepository ;
    @Autowired
    private ProvinceRepository provinceRepository ;
    @Autowired
    private CommunicablelevelRepository communicablelevelRepository ;

    RiskareaController(RiskareaRepository riskareaRepository) {
        this.riskareaRepository = riskareaRepository ;
    }

    @GetMapping("/riskarea")
    public Collection<Riskarea> riskareas() {
        return riskareaRepository.findAll().stream().collect(Collectors.toList()) ;
    }

    @PostMapping("/riskarea/{province_id}/{disease_id}/{communicablelevel_id}/{savedate}")
    public Riskarea newRiskarea( Riskarea newRiskarea,
    @PathVariable long province_id,
    @PathVariable long disease_id,
    @PathVariable long communicablelevel_id,
    @PathVariable String savedate) {

        Province province = provinceRepository.findById(province_id) ;
        Disease disease = diseaseRepository.findById(disease_id) ;
        Communicablelevel communicablelevel = communicablelevelRepository.findById(communicablelevel_id) ;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse(savedate);
            newRiskarea.setDate(date) ;
        } catch (ParseException e) {
        }

        newRiskarea.setProvince(province) ;
        newRiskarea.setDisease(disease) ;
        newRiskarea.setCommunicablelevel(communicablelevel) ;

        return riskareaRepository.save(newRiskarea) ;
    }
}
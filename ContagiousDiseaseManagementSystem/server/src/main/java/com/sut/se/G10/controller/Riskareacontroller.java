package com.sut.se.G10.controller;

import com.sut.se.G10.repository.Riskarearepository;
import com.sut.se.G10.repository.Diseaserepository;
import com.sut.se.G10.repository.Provincerepository;
import com.sut.se.G10.repository.Communicablelevelrepository;
import com.sut.se.G10.entity.Riskarea;
import com.sut.se.G10.entity.Province;
import com.sut.se.G10.entity.Disease;
import com.sut.se.G10.entity.Communicablelevel;

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
public class Riskareacontroller {
    @Autowired
    private final Riskarearepository riskarearepository ;
    @Autowired
    private Diseaserepository diseaserepository ;
    @Autowired
    private Provincerepository provincerepository ;
    @Autowired
    private Communicablelevelrepository communicablelevelrepository ;

    Riskareacontroller(Riskarearepository riskarearepository) {
        this.riskarearepository = riskarearepository ;
    }

    @GetMapping("/riskarea")
    public Collection<Riskarea> riskareas() {
        return riskarearepository.findAll().stream().collect(Collectors.toList()) ;
    }

    @PostMapping("/riskarea/{province_id}/{disease_id}/{communicablelevel_id}/{savedate}")
    public Riskarea newRiskarea( Riskarea newRiskarea,
    @PathVariable long province_id,
    @PathVariable long disease_id,
    @PathVariable long communicablelevel_id,
    @PathVariable String savedate) {

        Province province = provincerepository.findById(province_id) ;
        Disease disease = diseaserepository.findById(disease_id) ;
        Communicablelevel communicablelevel = communicablelevelrepository.findById(communicablelevel_id) ;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd") ;
        try {
            Date date = formatter.parse(savedate);
            newRiskarea.setDate(date) ;
        } catch (ParseException e) {
        }

        newRiskarea.setProvince(province) ;
        newRiskarea.setDisease(disease) ;
        newRiskarea.setCommunicablelevel(communicablelevel) ;

        return riskarearepository.save(newRiskarea) ;
    }
}
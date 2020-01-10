package com.sut.se.G10.Register.Controller;

import com.sut.se.G10.Register.Entity.Province;
import com.sut.se.G10.Register.Repository.ProvinceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ProvinceController {

    @Autowired
    private final ProvinceRepository provincerepository;

    public ProvinceController(ProvinceRepository provincerepository) {
        this.provincerepository = provincerepository;
    }

    @GetMapping("/province")
    public Collection<Province> provinces() {
        return provincerepository.findAll().stream().collect(Collectors.toList());
    }

}
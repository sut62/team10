package com.sut.se.G10.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.se.G10.entity.Communicablelevel;
import com.sut.se.G10.repository.Communicablelevelrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class Communicablelevelcontroller {
    
    @Autowired
    private final Communicablelevelrepository communicablelevelrepository ;

    public Communicablelevelcontroller(Communicablelevelrepository communicablelevelrepository) {
        this.communicablelevelrepository = communicablelevelrepository ;
    }

    @GetMapping("/communicablelevel")
    public Collection<Communicablelevel> communicablelevels() {
        return communicablelevelrepository.findAll().stream().collect(Collectors.toList());
    }
}
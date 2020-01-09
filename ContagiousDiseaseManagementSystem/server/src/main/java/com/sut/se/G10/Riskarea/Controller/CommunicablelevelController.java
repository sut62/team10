package com.sut.se.G10.Riskarea.Controller;

import java.util.Collection;
import java.util.stream.Collectors;

import com.sut.se.G10.Riskarea.Entity.Communicablelevel;
import com.sut.se.G10.Riskarea.Repository.CommunicablelevelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class CommunicablelevelController {
    
    @Autowired
    private final CommunicablelevelRepository communicablelevelRepository ;

    public CommunicablelevelController(CommunicablelevelRepository communicablelevelRepository) {
        this.communicablelevelRepository = communicablelevelRepository ;
    }

    @GetMapping("/communicablelevel")
    public Collection<Communicablelevel> communicablelevels() {
        return communicablelevelRepository.findAll().stream().collect(Collectors.toList());
    }
}
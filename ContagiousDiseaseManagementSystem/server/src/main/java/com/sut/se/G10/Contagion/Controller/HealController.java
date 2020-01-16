package com.sut.se.G10.Contagion.Controller;

import com.sut.se.G10.Contagion.Entity.Heal;
import com.sut.se.G10.Contagion.Repository.HealRepository;

import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class HealController {

    @Autowired
    private final HealRepository healRepository;

    public HealController(HealRepository healRepository) {
        this.healRepository = healRepository;
    }

    @GetMapping("/heal")
    public Collection<Heal> heals() {
        return healRepository.findAll().stream().collect(Collectors.toList());
    }
}
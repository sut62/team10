package com.sut.se.G10.Controller;

import com.sut.se.G10.Entity.Rate;
import com.sut.se.G10.Repository.RateRepository;

import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class RateController {

    @Autowired
    private final RateRepository rateRepository;

    public RateController(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @GetMapping("/rate")
    public Collection<Rate> rates() {
        return rateRepository.findAll().stream().collect(Collectors.toList());
    }

    // @GetMapping("/rate/{id}")
    // public Optional<Rate> Durations(@PathVariable Long id) {
    //     Optional<Rate> rate = rateRepository.findById(id);
    //     return rate;
    // }
}
package com.sut.se.G10.Register.Controller;

import com.sut.se.G10.Register.Entity.Position;
import com.sut.se.G10.Register.Repository.PositionRepository;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class PositionController {

    @Autowired
    private final PositionRepository positionRepository;

    public PositionController(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @GetMapping("/position")
    public Collection<Position> positions() {
        return positionRepository.findAll().stream().collect(Collectors.toList());
    }

}


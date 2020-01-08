package com.sut.se.G10.Controller;

import com.sut.se.G10.Entity.Type;
import com.sut.se.G10.Repository.TypeRepository;

import java.util.Collection;

import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class TypeController {

    @Autowired
    private final TypeRepository typeRepository;

    public TypeController(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @GetMapping("/type")
    public Collection<Type> types() {
        return typeRepository.findAll().stream().collect(Collectors.toList());
    }

    // @GetMapping("/type/{id}")
    // public Optional<Type> Type(@PathVariable Long id) {
    //     Optional<Type> type = typeRepository.findById(id);
    //     return type;
    // }
}
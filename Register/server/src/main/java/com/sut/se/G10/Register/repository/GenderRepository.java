package com.sut.se.G10.Register.repository;

import com.sut.se.G10.Register.entity.Gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Gender findById(long id);
}
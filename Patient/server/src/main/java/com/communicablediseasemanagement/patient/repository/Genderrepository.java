package com.communicablediseasemanagement.patient.repository;

import com.communicablediseasemanagement.patient.entity.Gender;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface Genderrepository extends JpaRepository<Gender, Long> {
    Gender findById(long id);
}
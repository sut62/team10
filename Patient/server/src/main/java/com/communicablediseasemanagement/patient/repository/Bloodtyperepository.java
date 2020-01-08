package com.communicablediseasemanagement.patient.repository;

import com.communicablediseasemanagement.patient.entity.Bloodtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Bloodtyperepository extends JpaRepository<Bloodtype, Long>{
    Bloodtype findById(long id);   
}
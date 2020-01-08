package com.sut.se.G10.Repository; 

import com.communicablediseasemanagement.riskarea.entity.Bloodtype;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface Bloodtyperepository extends JpaRepository<Bloodtype, Long>{
    Bloodtype findById(long id);   
}
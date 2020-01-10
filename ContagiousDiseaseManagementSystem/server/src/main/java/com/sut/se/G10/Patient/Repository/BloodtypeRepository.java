package com.sut.se.G10.Patient.Repository; 

import com.sut.se.G10.Patient.Entity.Bloodtype; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BloodtypeRepository extends JpaRepository<Bloodtype, Long>{
    Bloodtype findById(long id);   
}
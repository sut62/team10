package com.sut.se.G10.Repository; 

import com.communicablediseasemanagement.riskarea.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface Patientrepository extends JpaRepository<Patient, Long> {

}
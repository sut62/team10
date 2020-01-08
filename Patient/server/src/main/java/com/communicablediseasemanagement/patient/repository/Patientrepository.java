package com.communicablediseasemanagement.patient.repository;

import com.communicablediseasemanagement.patient.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface Patientrepository extends JpaRepository<Patient, Long> {

}
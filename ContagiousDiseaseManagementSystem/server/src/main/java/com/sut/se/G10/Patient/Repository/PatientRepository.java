package com.sut.se.G10.Patient.Repository;

import com.sut.se.G10.Patient.Entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findById(long id);
}
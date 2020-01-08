package com.sut.se.G10.repository;

import com.sut.se.G10.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PatientRepository extends JpaRepository<Patient, Long> {

}
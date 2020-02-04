package com.sut.se.G10.Patient.Repository;

import com.sut.se.G10.Patient.Entity.DiseasePatient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DiseasePatientRepository extends JpaRepository<DiseasePatient, Long> {

}
package com.sut.se.G10.Diagnose.Repository;

import com.sut.se.G10.Diagnose.Entity.Admission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface AdmissionRepository extends JpaRepository<Admission, Long> {
    Admission findById(long id);
    Admission findByAdmitted(String admitted);
}
package com.sut.se.G10.repository;

import com.sut.se.G10.entity.Admission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdmissionRepository extends JpaRepository<Admission, Long> {

    Admission findByAdmitted(String admitted);

}
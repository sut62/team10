package com.sut.se.G10.Register.repository;

import com.sut.se.G10.Register.entity.MedicalStaff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>{
	MedicalStaff findById(long id);
	MedicalStaff findByEmail(String email);
}
package com.sut.se.G10.VaccineInformation.Repository;

import com.sut.se.G10.VaccineInformation.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>{
    MedicalStaff findById(long id);
    MedicalStaff findByFullname(String fullname);
	// MedicalStaff findByEmail(String email);
}
package com.sut.se.G10.Register.Repository;

import java.util.Collection;
import java.util.Optional;

import com.sut.se.G10.Register.Entity.MedicalStaff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface MedicalStaffRepository extends JpaRepository<MedicalStaff, Long>{
	MedicalStaff findById(long id);

	Optional<MedicalStaff> findByEmail(String email);
	Optional<MedicalStaff> findByFullname(String fullname);

	
}
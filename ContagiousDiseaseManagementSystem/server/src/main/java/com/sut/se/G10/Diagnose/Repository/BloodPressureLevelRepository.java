package com.sut.se.G10.Diagnose.Repository;

import com.sut.se.G10.Diagnose.Entity.BloodPressureLevel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BloodPressureLevelRepository extends JpaRepository<BloodPressureLevel, Long> {

    BloodPressureLevel findById(long id);
    BloodPressureLevel findByLevel(String level);

}
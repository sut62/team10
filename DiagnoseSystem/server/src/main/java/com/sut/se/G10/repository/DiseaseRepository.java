package com.sut.se.G10.repository;

import com.sut.se.G10.entity.Disease;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

    Disease findByName(String name);
}
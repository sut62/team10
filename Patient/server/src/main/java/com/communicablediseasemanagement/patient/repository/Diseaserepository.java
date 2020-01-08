package com.communicablediseasemanagement.patient.repository;

import com.communicablediseasemanagement.patient.entity.Disease;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public
interface Diseaserepository extends JpaRepository<Disease, Long> {
    Disease findById(long id);
}
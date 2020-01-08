package com.sut.se.G10.Repository; 

import com.communicablediseasemanagement.riskarea.entity.Disease;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public
interface Diseaserepository extends JpaRepository<Disease, Long> {
    Disease findById(long id);
}
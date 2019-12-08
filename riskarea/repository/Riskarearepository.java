package com.communicablediseasemanagement.riskarea.repository;

import com.communicablediseasemanagement.riskarea.entity.Riskarea;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public
interface Riskarearepository extends JpaRepository<Riskarea, Long> {
}
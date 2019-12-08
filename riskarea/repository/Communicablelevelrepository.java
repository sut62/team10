package com.communicablediseasemanagement.riskarea.repository;

import com.communicablediseasemanagement.riskarea.entity.Communicablelevel;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public
interface Communicablelevelrepository extends JpaRepository<Communicablelevel, Long> {
    Communicablelevel findById(long id);
}
package com.communicablediseasemanagement.riskarea.repository;

import com.communicablediseasemanagement.riskarea.entity.Province;

import org.springframework.data.jpa.repository.JpaRepository;  
import org.springframework.data.rest.core.annotation.RepositoryRestResource;  
  
@RepositoryRestResource  
public
interface Provincerepository extends JpaRepository<Province, Long> {
    Province findById(long id);
}
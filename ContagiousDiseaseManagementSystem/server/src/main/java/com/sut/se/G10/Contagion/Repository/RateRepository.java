package com.sut.se.G10.Contagion.Repository; 

import com.sut.se.G10.Contagion.Entity.Rate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;    

@RepositoryRestResource 
public 
interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findById(long id);
}
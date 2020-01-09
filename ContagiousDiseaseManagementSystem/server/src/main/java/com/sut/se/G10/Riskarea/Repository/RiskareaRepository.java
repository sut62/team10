package com.sut.se.G10.Riskarea.Repository ;

import com.sut.se.G10.Riskarea.Entity.Riskarea ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface RiskareaRepository extends JpaRepository<Riskarea, Long> {
}
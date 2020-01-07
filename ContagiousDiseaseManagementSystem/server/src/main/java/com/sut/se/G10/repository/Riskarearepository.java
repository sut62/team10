package com.sut.se.G10.repository ;

import com.sut.se.G10.entity.Riskarea ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface Riskarearepository extends JpaRepository<Riskarea, Long> {
}
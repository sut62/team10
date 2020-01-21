package com.sut.se.G10.Diagnose.Repository;

import com.sut.se.G10.Diagnose.Entity.Diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.stereotype.Repository;

@RepositoryRestResource
public 
interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

}
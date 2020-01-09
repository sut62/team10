package com.sut.se.G10.Diagnose.Repository;

import com.sut.se.G10.Diagnose.Entity.Diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

}
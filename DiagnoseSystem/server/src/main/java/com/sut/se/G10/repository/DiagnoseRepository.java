package com.sut.se.G10.repository;

import com.sut.se.G10.entity.Diagnose;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

}
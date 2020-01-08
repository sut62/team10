package com.sut.se.G10.Register.repository;

import com.sut.se.G10.Register.entity.Province;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    Province findById(long id);
}
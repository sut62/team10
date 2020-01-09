package com.sut.se.G10.Register.repository;


import com.sut.se.G10.Register.entity.Position;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findById(long id);
}
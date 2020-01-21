package com.sut.se.G10.Riskarea.Repository;

import com.sut.se.G10.Riskarea.Entity.Riskarea;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface RiskareaRepository extends JpaRepository<Riskarea, Long> {

    // ค้นหาจากชื่อโรค
    @Query(value = "SELECT * FROM RISKAREA AS m WHERE m.DISEASE_ID = :id",nativeQuery = true)
    Collection<Riskarea> findByDisease(@Param("id") long id);
}
package com.sut.se.G10.VaccineInformation.Repository;

import com.sut.se.G10.VaccineInformation.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface TypeVaccineRepository extends JpaRepository<TypeVaccine, Long>{
    TypeVaccine findBytypevaccineid(long typevaccineid);     
}
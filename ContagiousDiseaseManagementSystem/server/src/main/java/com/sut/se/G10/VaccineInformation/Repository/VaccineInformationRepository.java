package com.sut.se.G10.VaccineInformation.Repository;

import com.sut.se.G10.VaccineInformation.Entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin
public interface VaccineInformationRepository extends JpaRepository<VaccineInformation, Long>{
    VaccineInformation findByvaccineinformationid(long vaccineinformationid);
}
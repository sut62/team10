package com.sut.se.G10.Riskarea.Repository ;

import com.sut.se.G10.Riskarea.Entity.Communicablelevel ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public 
interface CommunicablelevelRepository extends JpaRepository<Communicablelevel, Long> {
    Communicablelevel findById(long id) ;
}
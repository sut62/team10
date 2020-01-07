package com.sut.se.G10.repository ;

import com.sut.se.G10.entity.Communicablelevel ;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public 
interface Communicablelevelrepository extends JpaRepository<Communicablelevel, Long> {
    Communicablelevel findById(long id) ;
}
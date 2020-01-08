package com.sut.se.G10.Repository; 
import  com.sut.se.G10.Entity.Symptom; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;    
@RepositoryRestResource 
public interface SymptomRepository extends JpaRepository<Symptom, Long> {    
    Symptom findById(long symptomid);
}
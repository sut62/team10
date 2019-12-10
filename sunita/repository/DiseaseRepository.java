package com.sut.se.G10.Repository; 
import com.sut.se.G10.Entity.Disease; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;    
@RepositoryRestResource 
public interface DiseaseRepository extends JpaRepository<Disease, Long> {	
    Disease findById(long id);
}
package com.sut.se.G10.Repository; 
import com.sut.se.G10.Entity.Contagion; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;    
@RepositoryRestResource 
public 
interface ContagionRepository extends JpaRepository<Contagion, Long> {
    Contagion findById(long Contagionid);
}
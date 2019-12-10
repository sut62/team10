package com.sut.se.G10.Repository; 
import com.sut.se.G10.Entity.Type; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;    
@RepositoryRestResource 
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findById(long id);
}
package com.sut.se.G10.Statistics.Repository;

import com.sut.se.G10.Statistics.Entity.Statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public 
interface StatisticsRepository extends JpaRepository<Statistics, Long> {

}
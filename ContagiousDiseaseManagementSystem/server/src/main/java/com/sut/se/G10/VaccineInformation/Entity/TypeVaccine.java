package com.sut.se.G10.VaccineInformation.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="TYPEVACCINE")
public class TypeVaccine {
    @Id    
    @SequenceGenerator(name="typevaccine_SEQ",sequenceName="typevaccine_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="typevaccine_SEQ")  
    @Column(name="TYPEVACCINE_ID",unique = true, nullable = true)
    private @NonNull Long typevaccineid;
    private @NonNull String typevaccinelist;
   
  
}
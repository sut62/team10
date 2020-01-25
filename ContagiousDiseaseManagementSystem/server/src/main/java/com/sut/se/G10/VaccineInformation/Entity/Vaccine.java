package com.sut.se.G10.VaccineInformation.Entity;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="VACCINE")
public class Vaccine {
    @Id    
    @SequenceGenerator(name="vaccine_SEQ",sequenceName="vaccine_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vaccine_SEQ")  
    @Column(name="VACCINE_ID",unique = true, nullable = true)
    private @NotNull Long vaccineid;
    private @NotNull String vaccinename;
    private @NotNull String vaccinedata;
   
}
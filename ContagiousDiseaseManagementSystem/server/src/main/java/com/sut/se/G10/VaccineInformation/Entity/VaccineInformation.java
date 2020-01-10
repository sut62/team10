package com.sut.se.G10.VaccineInformation.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sut.se.G10.Register.Entity.MedicalStaff;

@Data
@Entity
@NoArgsConstructor
@Table(name="VACCINEINFORMATION")
public class VaccineInformation {
    @Id    
    @SequenceGenerator(name="vaccineinformation_SEQ",sequenceName="vaccineinformation_SEQ")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="vaccineinformation_SEQ")  
    @Column(name="VACCINEINFORMATION_ID",unique = true, nullable = true)
    private @NonNull Long vaccineinformationid;

    @ManyToOne
    @JoinColumn(name = "MEDICALSTAFF")
    private MedicalStaff fullname;

    @ManyToOne
    @JoinColumn(name = "VACCINE")
    private Vaccine vaccineid;

    @ManyToOne
    @JoinColumn(name = "TYPEVACCINE")
    private TypeVaccine typevaccineid;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private @NonNull Date storagedate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private @NonNull Date expiredate;
   
  
}
package com.sut.se.G10.entity;

import java.util.Date;
  
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Entity
@Table
@Data
@Setter @Getter
@NoArgsConstructor
public class Patient {  
      
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PATIENT_SEQ")
  @SequenceGenerator(name="PATIENT_SEQ",sequenceName="PATIENT_SEQ")
  @Column(name = "PATIENT_ID", unique = true, nullable = false)    
  private Long id;  

  @NotNull
  private Date patientdate;  
      
  @NotNull
  private String fullname;

  @NotNull
  private Date birthdate;

  @NotNull
  @Pattern(regexp = "[0-9]+")
  private String phone;

  @NotNull
  @Size(min = 10)
  private String address;

}
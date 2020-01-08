package com.sut.se.G10.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  
@Entity
@Table
@Data
@Setter @Getter
@NoArgsConstructor
public class Admission {  
      
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMISSION_SEQ")
  @SequenceGenerator(name="ADMISSION_SEQ",sequenceName="ADMISSION_SEQ")
  @Column(name = "ADMISSION_ID", unique = true, nullable = false)  
  private Long id;  

  @NotNull
  private String admitted;
}
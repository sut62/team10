package com.sut.se.G10.Diagnose.Entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="ADMISSION")
public class Admission {  
      
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADMISSION_SEQ")
  @SequenceGenerator(name="ADMISSION_SEQ",sequenceName="ADMISSION_SEQ")
  @Column(name = "ADMISSION_ID", unique = true, nullable = false)  
  private @NotNull Long id;  
  private @NotNull String admitted;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<Diagnose> diagnose ;
}
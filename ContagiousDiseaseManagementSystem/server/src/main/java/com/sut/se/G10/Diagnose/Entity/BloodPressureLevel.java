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
@Setter
@Getter
@NoArgsConstructor
@Table(name="BLOOD_PRESSUE_LEVEL")
public class BloodPressureLevel {  
      
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLOOD_PRESSURE_LEVEL_SEQ")
  @SequenceGenerator(name="BLOOD_PRESSURE_LEVEL_SEQ",sequenceName="BLOOD_PRESSURE_LEVEL_SEQ")
  @Column(name = "BLOOD_PRESSURE_LEVEL_ID", unique = true, nullable = false)
  private Long id;
  
  @NotNull
  @Column(name = "BLOOD_PRESSURE_LEVEL_LEVEL")
  private String level;

}
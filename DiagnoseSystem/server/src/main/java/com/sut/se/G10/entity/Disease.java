package com.sut.se.G10.entity;
  
import javax.persistence.Column;
import javax.persistence.Entity;
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
import lombok.NonNull;
  
@Entity
@Table
@Data
@Setter @Getter
@NoArgsConstructor
public class Disease {  
      
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISEASE_SEQ")
  @SequenceGenerator(name="DISEASE_SEQ",sequenceName="DISEASE_SEQ")
  @Column(name = "DISEASE_ID", unique = true, nullable = false)    
  private Long id;  

  @NotNull
  private String name;  
      
}
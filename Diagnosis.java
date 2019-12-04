package com.sut.se.G10.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Data
@Setter @Getter
@NoArgsConstructor
@SequenceGenerator(name = "diagnosis_seq")
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "diagnosis_seq")
    private Long id;

    @NotNull
    private Date diagnosisDate;

    @NotNull
    private String diagnosis;

    @NotNull
    private String stayAlertedTime;

    @ManyToOne
    private Admission admission;
}
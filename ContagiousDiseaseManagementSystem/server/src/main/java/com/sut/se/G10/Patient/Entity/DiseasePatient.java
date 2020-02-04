package com.sut.se.G10.Patient.Entity;

import com.sut.se.G10.Contagion.Entity.Disease;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
@NoArgsConstructor
@Table(name="Disease_Patient")
public class DiseasePatient {
	@Id
	@SequenceGenerator(name="DISEASE_PATIENT_SEQ",sequenceName="DISEASE_PATIENT_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DISEASE_PATIENT_SEQ")
	@Column(name="DISEASE_PATIENT_ID",unique = true, nullable = false)
	private Long id;

    @NotNull
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DISEASE_ID")
    private Disease disease;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="PATIENT_ID")
    private Patient patient;

}
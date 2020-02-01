package com.sut.se.G10.Diagnose.Entity;

import com.sut.se.G10.Contagion.Entity.Disease;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="Diagnose_Disease")
public class DiagnoseDisease {
	@Id
	@SequenceGenerator(name="DIAGNOSE_DISEASE_SEQ",sequenceName="DIAGNOSE_DISEASE_SEQ")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DIAGNOSE_DISEASE_SEQ")
	@Column(name="DIAGNOSE_DISEASE_ID",unique = true, nullable = false)
	private Long id;

    @NotNull
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DISEASE_ID")
    @JsonIgnore
    private Disease disease;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="DIAGNOSE_ID")
    @JsonIgnore
    private Diagnose diagnose;
}
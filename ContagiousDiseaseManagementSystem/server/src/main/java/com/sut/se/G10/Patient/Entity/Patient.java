package com.sut.se.G10.Patient.Entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sut.se.G10.Register.Entity.Gender;

import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "PATIENT")
public class Patient {

    @Id
    @SequenceGenerator(name = "Patient_seq", sequenceName = "Patient_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Patient_seq")
    @Column(name = "PATIENT_ID", unique = true, nullable = true)
    private Long id;

    @Column(name = "PERSON_ID", unique = true)
    @Pattern(regexp = "\\d{13}")
    private @NotNull String personId;

    @Size(min = 10, max = 50)
    private @NotNull String patientfullname;

    @Pattern(regexp = "\\d{10}")
    private @NotNull String phone;
    
    private @NotNull String address;

    @Temporal(TemporalType.DATE)
    private @NotNull Date patientbirthdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private @NotNull Date patientdate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Bloodtype.class)
    @JoinColumn(name = "BLOOD_ID", insertable = true)
    private @NotNull Bloodtype bloodtype ;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private @NotNull Gender gender;

}
package com.sut.se.G10.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter @Setter
@Data
@NoArgsConstructor
@SequenceGenerator(name = "patient_seq", initialValue = 1)
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    private Long id;

    private @NotNull String fullname;

    @ManyToOne
    private @NotNull Gender gender;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd")
    private @NotNull Date birthDate;

    @ManyToOne
    private @NotNull String bloodtype ;
    private @NotNull String address;

    @ManyToOne
    private @NotNull Contagion_Name contagion_name;
}
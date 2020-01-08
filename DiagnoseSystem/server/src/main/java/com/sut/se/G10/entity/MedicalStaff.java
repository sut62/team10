package com.sut.se.G10.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import java.util.Date;

import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "MEDICALSTAFF")
public class MedicalStaff {

    @Id
    @SequenceGenerator(name = "medicalstaff_seq", sequenceName = "medicalstaff_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicalstaff_seq")
    @Column(name = "MEDICALSTAFF_ID", unique = true, nullable = true)
    private Long id;
    private @NotNull String address;
    private @NotNull String fullname;

    @Email @Column(unique = true)
    private @NotNull String email;

    @Pattern(regexp = "[0-9]+")
    private @NotNull String phone;

    private @NotNull Date birthdate;

    private @NotNull String password;

    private @NotNull String position;
}
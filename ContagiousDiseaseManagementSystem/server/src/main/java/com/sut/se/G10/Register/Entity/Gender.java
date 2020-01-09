package com.sut.se.G10.Register.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Column;

@Data
@Entity
@NoArgsConstructor
@Table(name = "GENDER")
public class Gender {

    @Id
    @SequenceGenerator(name = "gender_seq", sequenceName = "gender_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_seq")
    @Column(name = "GENDER_ID", unique = true, nullable = true)
    private  Long id;
    private @NotNull String gender;

}




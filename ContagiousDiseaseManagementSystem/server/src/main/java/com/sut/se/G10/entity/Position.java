package com.sut.se.G10.Register.entity;

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
@Table(name = "POSITION")
public class Position {

    @Id
    @SequenceGenerator(name = "position_seq", sequenceName = "position_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "position_seq")
    @Column(name = "POSITION_ID", unique = true, nullable = true)
    private  Long id;

    private @NotNull String position;


}



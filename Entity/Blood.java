package com.sut.se.G10.entity;

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

@Entity
@Table
@Data
@Getter @Setter
@NoArgsConstructor
@SequenceGenerator(name = "blood_seq", initialValue = 1)
public class Blood {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blood_seq")
    private Long id;

    private @NotNull String gender;
}
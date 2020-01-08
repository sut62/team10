package com.sut.se.G10.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.Collection;
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
    @SequenceGenerator(name = "Gender_seq", sequenceName = "Gender_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Gender_seq")
    @Column(name = "Gender_ID", unique = true, nullable = true)
    private Long id;
    
    private @NotNull String gender;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Patient> patient;
}
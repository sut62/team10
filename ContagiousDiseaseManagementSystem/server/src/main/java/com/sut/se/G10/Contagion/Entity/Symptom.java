package com.sut.se.G10.Contagion.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.Collection;

import lombok.*;
import javax.persistence.Column;

@Data
@Entity
@NoArgsConstructor
@Table(name = "SYMPTOM")
public class Symptom {

    @Id
    @SequenceGenerator(name = "symptom_seq", sequenceName = "symptom_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "symptom_seq")
    @Column(name = "SYMPTOM_ID", unique = true, nullable = true)
    private @NotNull Long id;
    private @NotNull String symptom;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Contagion> contagion;


}


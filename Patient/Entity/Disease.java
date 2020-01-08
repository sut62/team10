package com.sut.se.G10.Entity;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.util.Collection;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

@Data
@Entity
@NoArgsConstructor
@Table(name="DISEASE")
public class Disease {

    @Id
    @SequenceGenerator(name="disease_seq",sequenceName="disease_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="disease_seq")  
    @Column(name = "DISEASE_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String disease;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Riskarea> riskarea;
}
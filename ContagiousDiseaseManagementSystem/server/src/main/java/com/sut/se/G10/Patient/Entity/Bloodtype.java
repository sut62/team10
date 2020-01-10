package com.sut.se.G10.Patient.Entity;

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
import javax.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "BLOODTYPE")
public class Bloodtype {

    @Id
    @SequenceGenerator(name = "Bloodtype_seq", sequenceName = "Bloodtype_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Bloodtype_seq")
    @Column(name = "BLOODTYPE_ID", unique = true, nullable = true)
    private Long id;
    
    private @NotNull String bloodtype;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Patient> patient;
}
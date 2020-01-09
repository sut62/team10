package com.sut.se.G10.Riskarea.Entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "COMMUNICABLELEVEL")
public class Communicablelevel {

    @Id
    @SequenceGenerator(name = "communicablelevel_seq", sequenceName = "communicablelevel_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "communicablelevel_seq")
    @Column(name = "COMMUNICABLELEVEL_ID", unique = true, nullable = true)
    private @NotNull Long id ;
    private @NotNull String communicablelevel ;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Riskarea> riskarea ;
} 
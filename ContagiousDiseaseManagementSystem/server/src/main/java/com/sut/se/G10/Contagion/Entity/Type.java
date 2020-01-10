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
@Table(name = "TYPE")
public class Type {

    @Id
    @SequenceGenerator(name = "type_seq", sequenceName = "type_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_seq")
    @Column(name = "TYPE_ID", unique = true, nullable = true)
    private @NotNull Long id;
    private @NotNull String type;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Contagion> contagion;
}



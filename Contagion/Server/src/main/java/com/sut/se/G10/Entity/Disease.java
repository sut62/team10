package com.sut.se.G10.Entity;

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

import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "DISEASE")
public class Disease {

    @Id
    @SequenceGenerator(name = "disease_seq", sequenceName = "disease_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disease_seq")
    @Column(name = "DISEASE_ID", unique = true, nullable = true)
    private @NotNull Long id;
    private @NotNull String disease;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Contagion> contagion;
}


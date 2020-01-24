package com.sut.se.G10.Contagion.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.persistence.FetchType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.persistence.Column;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "CONTAGION")
public class Contagion {

    @Id
    @SequenceGenerator(name = "contagion_seq", sequenceName = "contagion_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contagion_seq")
    @Column(name = "CONTAGION_ID", unique = true, nullable = true)
    private @NotNull Long id;

    @Size(min=3, max=10)
    @Pattern(regexp = "[a-z]*")
    private @NotNull String carrier;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "DISEASE_ID", insertable = true)
    private @NotNull Disease disease ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private @NotNull Type type;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Symptom.class)
    @JoinColumn(name = "SYMPTOM_ID", insertable = true)
    private @NotNull Symptom symptom;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Rate.class)
    @JoinColumn(name = "RATE_ID", insertable = true)
    private @NotNull Rate rate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Heal.class)
    @JoinColumn(name = "HEAL_ID", insertable = true)
    private @NotNull Heal heal ;

}


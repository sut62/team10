package com.sut.se.G10.Statistics.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Contagion.Entity.Type;
import com.sut.se.G10.Register.Entity.Province;

import javax.persistence.FetchType;

import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "STATISTICS")
public class Statistics {

    @Id
    @SequenceGenerator(name = "statistics_seq", sequenceName = "statistics_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistics_seq")
    @Column(name = "STATISTICS_ID", unique = true, nullable = true)
    private @NotNull Long id;

    @Pattern(regexp = "[a-z0-9]*")
    @Size(min = 2, max =10)
    private @NotNull String rates;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "DISEASE_ID", insertable = true)
    private @NotNull Disease disease ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Type.class)
    @JoinColumn(name = "TYPE_ID", insertable = true)
    private @NotNull Type type;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private @NotNull Province province;

}
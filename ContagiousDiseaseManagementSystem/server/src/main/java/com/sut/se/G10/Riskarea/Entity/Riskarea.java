package com.sut.se.G10.Riskarea.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Register.Entity.Province;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name="RISKAREA")
public class Riskarea {

    @Id
    @SequenceGenerator(name="riskarea_seq",sequenceName="riskarea_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="riskarea_seq")
    @Column(name = "RISKAREA_ID", unique = true, nullable = true)
    private @NotNull Long id ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private @NotNull Date date ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province province ;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "DISEASE_ID", insertable = true)
    private Disease disease;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Communicablelevel.class)
    @JoinColumn(name = "COMMUNICABLE_ID", insertable = true)
    private Communicablelevel communicablelevel;
}
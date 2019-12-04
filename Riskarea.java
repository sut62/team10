package com.sut.se.G10.Entity;  

import lombok.*;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
@Entity
@NoArgsConstructor
@Table(name="RISKAREA")
public class Riskarea {

    @Id
    @SequenceGenerator(name="riskarea_seq",sequenceName="riskarea_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="riskarea_seq")
    @Column(name = "RISKAREA_ID", unique = true, nullable = true)
    private @NonNull Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private @NonNull Date date;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private Province province;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Disease.class)
    @JoinColumn(name = "disease_ID", insertable = true)
    private Disease disease;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Communicablelevel.class)
    @JoinColumn(name = "COMMUNICABLE_ID", insertable = true)
    private Communicablelevel communicablelevel;
}
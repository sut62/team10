package com.communicablediseasemanagement.riskarea.entity; 

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
@Table(name="COMMUNICABLELEVEL")
public class Communicablelevel {

    @Id
    @SequenceGenerator(name="communicablelevel_seq",sequenceName="communicablelevel_seq")               
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="communicablelevel_seq")  
    @Column(name = "COMMUNICABLELEVEL_ID", unique = true, nullable = true)
    private @NonNull Long id;
    private @NonNull String communicablelevel;

    @OneToMany(fetch = FetchType.EAGER)
    private Collection<Riskarea> riskarea;
}
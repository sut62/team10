package com.sut.se.G10.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Data
@Setter @Getter
@NoArgsConstructor
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIAGNOSE_SEQ")
    @SequenceGenerator(name="DIAGNOSE_SEQ",sequenceName="DIAGNOSE_SEQ")
    @Column(name = "DIAGNOSE_ID", unique = true, nullable = false)  
    private Long id;

    @NotNull
    @Column(name ="DIAGNOSIS_DATE")
    private Date diagnosisDate;

    @NotNull
    @Column(name ="DIAGNOSIS")
    private String diagnosis;

    @NotNull
    @Column(name ="STAY_ALERTED_TIME")
    private String stayAlertedTime;

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", insertable = true)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "MEDICAL_STAFF_ID", insertable = true)
    private MedicalStaff medicalStaff;

    @ManyToOne
    @JoinColumn(name = "DISEASE_NAME", insertable = true)
    private Disease disease;

    @ManyToOne
    @JoinColumn(name = "ADMISSION_ADMITTED", insertable = true)
    private Admission admission;

}
package com.sut.se.G10.Diagnose.Entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;   
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;

import com.sut.se.G10.Contagion.Entity.Disease;
import com.sut.se.G10.Patient.Entity.Patient;
import com.sut.se.G10.Register.Entity.MedicalStaff;

import com.fasterxml.jackson.annotation.*;

import lombok.*;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@Table(name="DIAGNOSE")
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIAGNOSE_SEQ")
    @SequenceGenerator(name="DIAGNOSE_SEQ",sequenceName="DIAGNOSE_SEQ")
    @Column(name = "DIAGNOSE_ID", unique = true, nullable = false)  
    private Long id;

    @NotNull
    @Column(name = "DIAGNOSE_CODE", unique = true)
    private String diagnoseCode;

    @NotNull
    @Column(name ="DIAGNOSIS_DATE")
    private Date diagnosisDate;

    @NotNull
    @Column(name ="DIAGNOSIS")
    @Size(min=10, max=100)
    @Pattern(regexp = "[a-zA-Z0-9._, \t]*")
    private String diagnosis;

    @NotNull
    @Column(name ="STAY_ALERTED_TIME")
    @Min(value = 0)
    @Max(value = 99999)
    private Long stayAlertedTime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", insertable = true)
    private Patient patient;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "MEDICAL_STAFF_ID", insertable = true)
    private MedicalStaff diagnosisDoctor;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "BLOOD_PRESSURE_LEVEL_ID", insertable = true)
    private BloodPressureLevel bloodPressureLevel;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ADMISSION_ID", insertable = true)
    private Admission admission;
}
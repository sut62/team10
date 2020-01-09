package com.sut.se.G10.Register.Entity;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import java.util.Date;

import javax.persistence.Column;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "MEDICALSTAFF")
public class MedicalStaff {

    @Id
    @SequenceGenerator(name = "medicalstaff_seq", sequenceName = "medicalstaff_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medicalstaff_seq")
    @Column(name = "MEDICALSTAFF_ID", unique = true, nullable = true)
    private @NotNull Long id;
    private @NotNull String address;
    private @NotNull String fullname;
    private @NotNull String positionString;

    @Email @Column(unique = true)
    // @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private @NotNull String email;

    @Pattern(regexp = "\\d{10}")
    private @NotNull String phone;

    private @NotNull Date birthdate;
    
    @Size(min = 8)
    private @NotNull String password;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Gender.class)
    @JoinColumn(name = "GENDER_ID", insertable = true)
    private @NotNull Gender gender ;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Position.class)
    @JoinColumn(name = "POSITION_ID", insertable = true)
    private @NotNull Position position;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Province.class)
    @JoinColumn(name = "PROVINCE_ID", insertable = true)
    private @NotNull Province province;
}


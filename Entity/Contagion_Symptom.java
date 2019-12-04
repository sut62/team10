package com.sut.se.G10.Entity;
//import java.util.*;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import java.util.List;
import lombok.*;
//import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Contagion_Symptom")
public class Contagion_Symptom {
 @Id

@SequenceGenerator(name="contagion_symptom_seq",sequenceName="contagion_symptom_seq")
 @GeneratedValue(strategy = GenerationType.SEQUENCE,
generator="contagion_symptom_seq")
 @Column(name = "SYMPTOM_ID", unique = true, nullable = true)
 private @NonNull Long symptomid;
 private @NonNull String symptom;
}
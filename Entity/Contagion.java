package com.sut.se.G10.Entity;
//import java.util.*;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.*;
//import com.fasterxml.jackson.annotation.JsonIgnore;
@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Contagion")
public class Contagion {
 @Id
 @SequenceGenerator(name="contagion_seq",sequenceName="contagion_seq")
 @GeneratedValue(strategy = GenerationType.SEQUENCE,
generator="contagion_seq")
 @Column(name = "CONTAGION_ID", unique = true, nullable = true)
 private @NonNull Long id;
 private @NonNull String carrier;

 @ManyToOne(fetch = FetchType.EAGER, targetEntity = Contagion_Name.class)
 @JoinColumn(name = "NAME_ID", insertable = true)
 private Contagion contagion_name;

 @ManyToOne(fetch = FetchType.EAGER, targetEntity = Contagion_Type.class)
 @JoinColumn(name = "TYPE_ID", insertable = true)
 private Contagion_Type type;

 @ManyToOne(fetch = FetchType.EAGER, targetEntity = Contagion_Symptom.class)
 @JoinColumn(name = "SYMPTOM_ID", insertable = true)
 private Contagion_Symptom symptom;

 @ManyToOne(fetch = FetchType.EAGER, targetEntity = Contagion_Rate.class)
 @JoinColumn(name = "RATE_ID", insertable = true)
 private Contagion_Rate rate;
}




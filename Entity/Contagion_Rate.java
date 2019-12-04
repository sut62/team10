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
@Table(name="Contagion_Rate")
public class Contagion_Rate {
 @Id

@SequenceGenerator(name="contagion_rate_seq",sequenceName="contagion_rate_seq")
 @GeneratedValue(strategy = GenerationType.SEQUENCE,
generator="contagion_rate_seq")
 @Column(name = "RATE_ID", unique = true, nullable = true)
 private @NonNull Long rateid;
 private @NonNull String rate;
}
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
@Table(name="Contagion_Contagion_Name ")
public class Contagion_Name {
 @Id

@SequenceGenerator(name="contagion_contagion_name _seq",sequenceName="contagion_contagion_name _seq")
 @GeneratedValue(strategy = GenerationType.SEQUENCE,
generator="contagion_contagion_name _seq")
 @Column(name = "CONTAGION_NAME_ID", unique = true, nullable = true)
 private @NonNull Long nameid;
 private @NonNull String name ;
}
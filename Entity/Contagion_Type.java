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
@Table(name="Contagion_Type")
public class Contagion_Type {
 @Id

@SequenceGenerator(name="contagion_type_seq",sequenceName="contagion_type_seq")
 @GeneratedValue(strategy = GenerationType.SEQUENCE,
generator="contagion_type_seq")
 @Column(name = "TYPE_ID", unique = true, nullable = true)
 private @NonNull Long typeid;
 private @NonNull String type;
}
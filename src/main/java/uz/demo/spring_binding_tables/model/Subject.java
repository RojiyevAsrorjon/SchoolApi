package uz.demo.spring_binding_tables.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private Set<TimeTable> timeTables = new HashSet<>();


}

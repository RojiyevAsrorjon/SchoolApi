package uz.demo.spring_binding_tables.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @ManyToOne
    School school;

    @ManyToOne
    private Subject subject;

    @ManyToMany
    private Set<Group> groups = new HashSet<>();

    @ManyToMany
    private Set<TimeTable> timeTables =  new HashSet<>();


}

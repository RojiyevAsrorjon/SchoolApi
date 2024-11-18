package uz.demo.spring_binding_tables.model.dtos;

import lombok.Getter;
import lombok.Setter;
import uz.demo.spring_binding_tables.model.Group;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TeacherDto {

    String name;
    String surname;
    Integer schoolId;
    Integer subjectId;
    Set<Integer> groupIds = new HashSet<>();
    Set<Integer> timeTableIds = new HashSet<>();
}

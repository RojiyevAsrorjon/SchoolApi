package uz.demo.spring_binding_tables.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public class SubjectDto {
    String name;

    HashSet<Integer> timeTableIds;
}

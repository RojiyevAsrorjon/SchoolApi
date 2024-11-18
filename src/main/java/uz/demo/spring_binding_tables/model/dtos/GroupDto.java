package uz.demo.spring_binding_tables.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Setter
@Getter
public class GroupDto {
    String groupName;
    Integer schoolId;
    HashSet<Integer> subjectIds;
}

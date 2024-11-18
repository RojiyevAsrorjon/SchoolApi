package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.Group;
import uz.demo.spring_binding_tables.model.School;
import uz.demo.spring_binding_tables.model.Subject;
import uz.demo.spring_binding_tables.model.dtos.GroupDto;
import uz.demo.spring_binding_tables.repository.GroupRepository;
import uz.demo.spring_binding_tables.repository.SchoolRepository;
import uz.demo.spring_binding_tables.repository.SubjectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class GroupController {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    SchoolRepository schoolRepository;

    @GetMapping("/school/groups")
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @GetMapping("/school/group/{id}")
    public Group getGroupById(@PathVariable int id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.orElse(null);
    }

    @PostMapping("/school/group")
    public String addGroup(@RequestBody GroupDto groupDTO) {
        Group group = new Group();
        group.setName(groupDTO.getGroupName());
        Optional<School> optionalSchool = schoolRepository.findById(groupDTO.getSchoolId());
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            group.setSchool(school);
            Set<Subject> subjects = new HashSet<>();
            for (Integer i : groupDTO.getSubjectIds()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(i);
                optionalSubject.ifPresent(subjects::add);
            }
            group.setSubjects(subjects);
            groupRepository.save(group);
            return "Group added successfully";
        }
        return "School with this ID does not exist";
    }
}

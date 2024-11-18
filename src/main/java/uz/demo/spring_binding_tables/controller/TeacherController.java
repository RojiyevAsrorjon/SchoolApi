package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.*;
import uz.demo.spring_binding_tables.model.dtos.TeacherDto;
import uz.demo.spring_binding_tables.repository.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
public class TeacherController {

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    TimeTableRepository timeTableRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/school/teacher")
    public List<Teacher> getTeachers() {
        return teacherRepository.findAll();
    }

    @GetMapping("/school/teacher/{id}")
    public Teacher getTeacher(@PathVariable Integer id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        return optionalTeacher.orElse(null);
    }

    @PostMapping("/school/teacher")
    public String addTeacher(@RequestBody TeacherDto teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setName(teacherDTO.getName());
        teacher.setSurname(teacherDTO.getSurname());

        Optional<School> optionalSchool = schoolRepository.findById(teacherDTO.getSchoolId());
        School school = optionalSchool.orElse(null);
        if (Objects.isNull(school)) {
            return "School with this id does not exist";
        }
        teacher.setSchool(school);

        Optional<Subject> optionalSubject = subjectRepository.findById(teacherDTO.getSubjectId());
        Subject subject = optionalSubject.orElse(null);
        if (Objects.isNull(subject)) {
            return "Subject with this id does not exist";
        }
        teacher.setSubject(subject);

        for (Integer i : teacherDTO.getGroupIds()) {
            Optional<Group> optionalGroup = groupRepository.findById(i);
            Group group = optionalGroup.orElse(null);
            if (!Objects.isNull(group)) {
                teacher.getGroups().add(group);
            }
        }

        for (Integer i : teacherDTO.getTimeTableIds()) {
            Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(i);
            TimeTable timeTable = optionalTimeTable.orElse(null);
            if (!Objects.isNull(timeTable)) {
                teacher.getTimeTables().add(timeTable);
            }
        }
        teacherRepository.save(teacher);
        return "Teacher added successfully";
    }
}

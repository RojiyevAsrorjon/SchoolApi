package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.Subject;
import uz.demo.spring_binding_tables.model.TimeTable;
import uz.demo.spring_binding_tables.model.dtos.SubjectDto;
import uz.demo.spring_binding_tables.repository.SubjectRepository;
import uz.demo.spring_binding_tables.repository.TimeTableRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    TimeTableRepository timeTableRepository;

    @GetMapping("/school/subject")
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @GetMapping("/school/subject/{id}")
    public Subject getSubject(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElse(null);
    }

    @PostMapping("/school/subject")
    public String addSubject(@RequestBody SubjectDto subjectDTO) {
        int count = subjectRepository.countSubjectByName(subjectDTO.getName());
        if (count == 0) {
            Subject subject = new Subject();
            subject.setName(subjectDTO.getName());
            for (Integer i : subjectDTO.getTimeTableIds()) {
                Optional<TimeTable> optionalTimeTable = timeTableRepository.findById(i);
                if (optionalTimeTable.isPresent()) {
                    TimeTable timeTable = optionalTimeTable.get();
                    subject.getTimeTables().add(timeTable);
                }
            }
            subjectRepository.save(subject);
            return "Successfully added subject";
        }
        return "Subject already exists";
    }

    @DeleteMapping("/school/school-delete/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            subjectRepository.deleteById(id);
            return "Successfully deleted subject";
        }
        return "Subject does not exist";
    }
}

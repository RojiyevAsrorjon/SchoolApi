package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.Mark;
import uz.demo.spring_binding_tables.model.Pupil;
import uz.demo.spring_binding_tables.model.Subject;
import uz.demo.spring_binding_tables.model.dtos.MarkDto;
import uz.demo.spring_binding_tables.repository.MarkRepository;
import uz.demo.spring_binding_tables.repository.PupilRepository;
import uz.demo.spring_binding_tables.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class MarkController {

    @Autowired
    MarkRepository markRepository;

    @Autowired
    PupilRepository pupilRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @GetMapping("/school/marks")
    public List<Mark> getMarks() {
        return markRepository.findAll();
    }

    @GetMapping("/school/mark/{id}")
    public Mark getMark(@PathVariable Integer id) {
        Optional<Mark> mark = markRepository.findById(id);
        return mark.orElse(null);
    }

    @PutMapping("/school/mark-edit/{id}")
    public String editMark(@PathVariable Integer id, @RequestBody MarkDto markDto) {
        Optional<Mark> markOptional = markRepository.findById(id);
        if (markOptional.isPresent()) {
            Mark mark = markOptional.get();
            Optional<Pupil> optionalPupil = pupilRepository.findById(markDto.getStudentId());
            if (optionalPupil.isPresent()) {
                Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
                if (optionalSubject.isPresent()) {
                    Pupil pupil = optionalPupil.get();
                    Subject subject = optionalSubject.get();
                    mark.setId(id);
                    mark.setValue(markDto.getValue());
                    mark.setSubjects(subject);
                    mark.setPupils(pupil);
                    markRepository.save(mark);
                    return "Mark saved";
                }
                return "Subject with this id not found";
            }
            return "Pupil with this id not found";
        }
        return "Mark with this id not found";
    }

    @PostMapping("/school/mark")
    public String addMark(@RequestBody MarkDto markDto) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(markDto.getStudentId());
        if (optionalPupil.isPresent()) {
            Optional<Subject> optionalSubject = subjectRepository.findById(markDto.getSubjectId());
            if (optionalSubject.isPresent()) {
                Pupil pupil = optionalPupil.get();
                Subject subject = optionalSubject.get();
                Mark mark = new Mark();
                mark.setValue(markDto.getValue());
                mark.setSubjects(subject);
                mark.setPupils(pupil);
                markRepository.save(mark);
                return "Mark saved";
            }
            return "Subject with this id not found";
        }
        return "Pupil with this id not found";
    }
}


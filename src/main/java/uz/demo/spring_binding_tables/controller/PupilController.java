package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.Group;
import uz.demo.spring_binding_tables.model.Pupil;
import uz.demo.spring_binding_tables.model.dtos.PupilDto;
import uz.demo.spring_binding_tables.repository.GroupRepository;
import uz.demo.spring_binding_tables.repository.PupilRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class PupilController {

    @Autowired
    PupilRepository pupilRepository;
    @Autowired
    GroupRepository groupRepository;

    @GetMapping("/school/pupils")
    public List<Pupil> getPupils() {
        return pupilRepository.findAll();
    }

    @GetMapping("/school/pupil/{id}")
    public Pupil getPupil(@PathVariable Integer id) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(id);
        Pupil pupil = optionalPupil.orElse(null);
        return pupil;
    }

    @PostMapping("/school/student")
    public String addPupil(@RequestBody PupilDto pupilDto) {
        Optional<Group> optionalGroup = groupRepository.findById(pupilDto.getGroupId());
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            Pupil pupil = new Pupil();
            pupil.setName(pupilDto.getName());
            pupil.setSurname(pupilDto.getSurname());
            pupil.setGroup(group);
            pupilRepository.save(pupil);
            return "Pupil added successfully";
        }
        return "Group with this id does not exist";
    }

    @PutMapping("/school/student-edit/{id}")
    public String editPupil(@RequestBody PupilDto pupilDto, @PathVariable Integer id) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(id);
        if (optionalPupil.isPresent()) {
            Optional<Group> optionalGroup = groupRepository.findById(pupilDto.getGroupId());
            if (optionalGroup.isPresent()) {
                Group group = optionalGroup.get();
                Pupil pupil = optionalPupil.get();
                pupil.setId(id);
                pupil.setName(pupilDto.getName());
                pupil.setSurname(pupilDto.getSurname());
                pupil.setGroup(group);
                pupilRepository.save(pupil);
                return "Pupil edited successfully";
            }
            return "Group with this id does not exist";
        }
        return "Pupil with this id does not exist";
    }

    @DeleteMapping("/school/pupil-delete/{id}")
    public String deletePupil(@PathVariable Integer id) {
        Optional<Pupil> optionalPupil = pupilRepository.findById(id);
        if (optionalPupil.isPresent()) {
            pupilRepository.deleteById(id);
            return "Pupil deleted successfully";
        }
        return "Pupil with this id does not exist";
    }
}

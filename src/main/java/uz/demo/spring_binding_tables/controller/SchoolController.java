package uz.demo.spring_binding_tables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.Address;
import uz.demo.spring_binding_tables.model.School;
import uz.demo.spring_binding_tables.model.dtos.SchoolDto;
import uz.demo.spring_binding_tables.repository.AddressRepository;
import uz.demo.spring_binding_tables.repository.SchoolRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class SchoolController {

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    AddressRepository addressRepository;


    @GetMapping("/school")
    public List<School> getSchools() {
        return schoolRepository.findAll();
    }

    @GetMapping("/school/{id}")
    public School getSchoolById(@PathVariable Integer id) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        return optionalSchool.orElse(null);
    }

    /* Method for getting schools */
    @PostMapping("/school")
    public String addSchool(@RequestBody SchoolDto schoolDTO) {
        Address add = new Address();
        add.setRegion(schoolDTO.getRegion());
        add.setStreet(schoolDTO.getStreet());
        add.setDistrict(schoolDTO.getDistrict());
        addressRepository.save(add);
        School school = new School();
        school.setName(schoolDTO.getName());
        school.setAddress(add);
        schoolRepository.save(school);
        return "Successfully added school";
    }

    /*Method for editing schools by id*/
    @PutMapping("/school-edit/{id}")
    public String updateSchool(@PathVariable int id, @RequestBody SchoolDto schoolDTO) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            School school = optionalSchool.get();
            school.setName(schoolDTO.getName());
            Address address = school.getAddress();
            address.setId(address.getId());
            address.setStreet(schoolDTO.getStreet());
            address.setDistrict(schoolDTO.getDistrict());
            address.setRegion(schoolDTO.getRegion());
            school.setAddress(address);
            addressRepository.save(address);
            schoolRepository.save(school);
            return "Successfully updated school";
        }
        return "School not found";
    }

    @DeleteMapping("/school-delete/{id}")
    public String deleteSchoolById(@PathVariable Integer id) {
        Optional<School> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()) {
            schoolRepository.deleteById(id);
            return "Successfully deleted school";
        }
        return "School not found";
    }
}

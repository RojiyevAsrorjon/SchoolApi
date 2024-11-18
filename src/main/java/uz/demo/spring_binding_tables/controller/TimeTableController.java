package uz.demo.spring_binding_tables.controller;

import org.hibernate.cache.spi.TimestampsRegion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.demo.spring_binding_tables.model.TimeTable;
import uz.demo.spring_binding_tables.repository.TimeTableRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class TimeTableController {

    @Autowired
    TimeTableRepository timeTableRepository;

    @GetMapping("/school/time-table")
    public List<TimeTable> getTimeTable() {
        return timeTableRepository.findAll();
    }

    @PostMapping("/school/time-table")
    public String addTimeTable(@RequestBody TimeTable timeTable) {
        int count = timeTableRepository.countByStartTimeAndEndTime(timeTable.getStartTime(), timeTable.getEndTime());
        if (count == 0) {
            timeTableRepository.save(timeTable);
            return "Successfully added time table";
        } else {
            return "Time table already exists";
        }
    }

    @DeleteMapping("/school/time-table-delete/{id}")
    public String deleteTimeTableById(@PathVariable Integer id) {
        Optional<TimeTable> timeTable = timeTableRepository.findById(id);
        if (timeTable.isPresent()) {
            timeTableRepository.deleteById(id);
            return "Successfully deleted time table";
        }
        return "Time table does not exist";
    }

    @PutMapping("/school/time-table-update/{id}")
    public String updateTimeTableById(@PathVariable Integer id, @RequestBody TimeTable timeTable) {
        Optional<TimeTable> timeTableOptional = timeTableRepository.findById(id);
        if (timeTableOptional.isPresent()) {
            timeTable.setId(id);
            timeTableRepository.save(timeTable);
            return "Successfully updated time table";
        }
        return "Time table does not exist";
    }
}

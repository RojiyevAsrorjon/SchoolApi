package uz.demo.spring_binding_tables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.spring_binding_tables.model.TimeTable;

public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
    int countByStartTimeAndEndTime(String startTime, String endTime);
}

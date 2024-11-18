package uz.demo.spring_binding_tables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.spring_binding_tables.model.Pupil;

public interface PupilRepository extends JpaRepository<Pupil, Integer> {
}

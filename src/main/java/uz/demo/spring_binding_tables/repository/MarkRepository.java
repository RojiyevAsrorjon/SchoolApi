package uz.demo.spring_binding_tables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.spring_binding_tables.model.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer> {
}

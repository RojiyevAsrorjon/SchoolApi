package uz.demo.spring_binding_tables.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.demo.spring_binding_tables.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    int countSubjectByName(String name);
}

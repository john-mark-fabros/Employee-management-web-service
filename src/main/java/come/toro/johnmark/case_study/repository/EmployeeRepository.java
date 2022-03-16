package come.toro.johnmark.case_study.repository;

import come.toro.johnmark.case_study.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * John Mark A. Fabros
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

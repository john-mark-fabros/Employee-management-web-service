package com.johnmark.case_study.repository;

import com.johnmark.case_study.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * John Mark A. Fabros
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByFirstName(String firstName);
    Optional<Employee> findEmployeeByEmail(String email);
}

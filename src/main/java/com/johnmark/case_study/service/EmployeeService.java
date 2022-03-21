package com.johnmark.case_study.service;

import com.johnmark.case_study.model.Employee;
import com.johnmark.case_study.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * John Mark A. Fabros
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> listEmployees() {
        return employeeRepository.findAll();
    }

    public void addNewEmployee(Employee employee) {
        Optional<Employee> emp = employeeRepository.findEmployeeByFirstName(employee.getFirstName());
        if (emp.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FOUND, "Employee already exist");
        }
        employeeRepository.save(employee);
        System.out.println("This is the employee data--> "+ employee);
    }

    public void deleteEmployee(Integer id) {
        boolean checkEmployee = employeeRepository.existsById(id);
        if (checkEmployee) {
            employeeRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Transactional
    public void updateEmployee(Integer id, String email) {

        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee don't exist"));
        if ((email != null) && (email.length() > 0) && (!Objects.equals(employee.getEmail(), email))) {
            Optional<Employee> emp = employeeRepository.findEmployeeByEmail(email);
            if (emp.isPresent()) {
                throw new ResponseStatusException(HttpStatus.FOUND, "Email already exist");
            }
            employee.setEmail(email);
        }
    }

    public List<Employee> getEmployeeByFirstName(String firstName) {
        Optional<Employee> emp = employeeRepository.findEmployeeByFirstName(firstName);
        if (emp.isPresent()) {
            return emp.stream().toList();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, firstName +" does not exist");
    }

    public List<Employee> pageEmployee(int pageNo, int pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Employee> pagedResult = employeeRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<Employee> sortEmployees(String sort) {
        Sort sortOrder = Sort.by(sort);
        return employeeRepository.findAll(sortOrder);
    }
}




















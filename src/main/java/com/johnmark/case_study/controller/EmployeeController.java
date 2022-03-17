package com.johnmark.case_study.controller;

import com.johnmark.case_study.model.Employee;
import com.johnmark.case_study.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * John Mark A. Fabros
 */
@RestController
@RequestMapping(path = "api/v1/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> listEmployee() {
        return employeeService.listEmployees();
    }

    @PostMapping
    public void saveEmployee(@Valid @RequestBody Employee employee) {
        employeeService.addNewEmployee(employee);
    }

    @DeleteMapping(path = "{id}")
    public void deleteEmployee(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping(path = "{id}")
    public void updateEmployee(@PathVariable("id") Integer id,
                               @RequestParam(required = false) String email) {
        employeeService.updateEmployee(id, email);
    }

    @GetMapping(path = "/employee/{firstName}")
    public List<Employee> getSpecificEmployee(@PathVariable("firstName") String firstName) {
        return employeeService.getEmployeeByFirstName(firstName);
    }

    @GetMapping(path = "{pageNo}/{pageSize}")
    public List<Employee> pageEmployee(@PathVariable Integer pageNo, @PathVariable Integer pageSize) {
        return employeeService.pageEmployee(pageNo, pageSize);
    }

    @GetMapping(path = "/sort/{sort}")
    public List<Employee> sortEmployeeByFirstName(@PathVariable String sort) {
        return employeeService.sortEmployees(sort);
    }
}

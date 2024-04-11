package pro.sky.collection.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collection.Employee;
import pro.sky.collection.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "salary", required = false) Integer salary,
            @RequestParam(value = "department", required = false) Integer department) {
        return employeeService.addEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "salary", required = false) Integer salary,
            @RequestParam(value = "department", required = false) Integer department) {
        return employeeService.findEmployee(firstName, lastName, salary, department);
    }

    @GetMapping(path = "/all")
    public Collection<Employee> findAll() {
        return employeeService.findAll();
    }
}

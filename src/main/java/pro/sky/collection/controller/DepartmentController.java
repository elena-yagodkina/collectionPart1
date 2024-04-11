package pro.sky.collection.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.collection.Employee;
import pro.sky.collection.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    public final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryDepartment(@RequestParam int department) {
        return departmentService.findEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryDepartment(@RequestParam int department) {
        return departmentService.findEmployeeWithMinSalary(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findEmployees() {
        return departmentService.findEmployeesByDepartmentSortedByNameSurname();
    }

    @GetMapping(value = "/all", params = {"department"})
    public Collection<Employee> findEmployees(@RequestParam int department) {
        return departmentService.findEmployeesByDepartmentSortedByNameSurname(department);
    }
}

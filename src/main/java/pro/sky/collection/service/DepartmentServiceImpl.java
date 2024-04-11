package pro.sky.collection.service;

import org.springframework.stereotype.Service;
import pro.sky.collection.Employee;
import pro.sky.collection.exception.EmployeeNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee findEmployeeWithMaxSalary(int department) {
        return employeeService
                .findAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Employee findEmployeeWithMinSalary(int department) {
        return employeeService
                .findAll()
                .stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    @Override
    public Map<Integer, List<Employee>> findEmployeesByDepartmentSortedByNameSurname() {
        return employeeService
                .findAll()
                .stream()
                .sorted(comparing(Employee::getLastName).thenComparing(Employee::getFirstName))
                .collect(groupingBy(Employee::getDepartment));
    }

    @Override
    public Collection<Employee> findEmployeesByDepartmentSortedByNameSurname(int department) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .sorted(comparing(Employee::getLastName)
                        .thenComparing(Employee::getFirstName))
                .collect(toList());
    }
}

package pro.sky.collection.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pro.sky.collection.Employee;
import pro.sky.collection.exception.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class DepartmentServiceTest {
    private DepartmentServiceImpl departmentService;
    private EmployeeService employeeService;

    private List<Employee> employees = List.of(
            new Employee("Сергей", "Петров", 60000, 1),
            new Employee("Андрей", "Иванов", 55000, 1),
            new Employee("Ирина", "Арбузова", 40000, 2));

    @BeforeEach
    public void init() {
        employeeService = Mockito.mock(EmployeeService.class);
        departmentService = new DepartmentServiceImpl(employeeService);
    }

    @Test
    public void returnEmployeeWithMaxSalary() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee employee = departmentService.findEmployeeWithMaxSalary(1);
        assertEquals("Сергей", employee.getFirstName());
        assertEquals("Петров", employee.getLastName());
    }

    @Test
    public void returnEmployeeWithMinSalary() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee employee = departmentService.findEmployeeWithMinSalary(2);
        assertEquals("Ирина", employee.getFirstName());
        assertEquals("Арбузова", employee.getLastName());
    }

    @Test
    public void trowExceptionEmployeeWithMaxSalary() {
        when(employeeService.findAll()).thenReturn(employees);
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeWithMaxSalary(5));
    }

    @Test
    public void trowExceptionEmployeeWithMinSalary() {
        when(employeeService.findAll()).thenReturn(employees);
        assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeWithMinSalary(5));
    }

    @Test
    public void returnAllEmployeeGroupedByDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Map<Integer, List<Employee>> result = departmentService.findEmployeesByDepartmentSortedByNameSurname();
        assertEquals(2, result.size());
        assertEquals(2, result.get(1).size());
        assertEquals(1, result.get(2).size());

        org.assertj.core.api.Assertions.assertThat(result.get(1))
                .hasSize(2)
                .contains(employees.get(0), employees.get(1));

        org.assertj.core.api.Assertions.assertThat(result.get(2))
                .hasSize(1)
                .contains(employees.get(2));
    }
}

package pro.sky.collection.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import pro.sky.collection.Employee;
import pro.sky.collection.exception.BadRequestException;
import pro.sky.collection.exception.EmployeeAlreadyAddedException;
import pro.sky.collection.exception.EmployeeNotFoundException;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {
    private EmployeeService service = new EmployeeServiceImpl();

    @Test
    public void addNewEmployee() {
        Employee employee = service.addEmployee("Роман", "Давыдов", 80000, 1);
        assertEquals("Роман", employee.getFirstName());
        assertEquals("Давыдов", employee.getLastName());
        assertEquals(80000, employee.getSalary());
        assertEquals(1, employee.getDepartment());

        assertEquals(service.findEmployee("Роман", "Давыдов", 80000, 1), employee);
    }

    @Test
    public void addEmployeeWithIncorrectData() {
        assertThrows(BadRequestException.class, () -> service.addEmployee("2", "Давыдов", 80000, 1));
        assertThrows(BadRequestException.class, () -> service.addEmployee("Роман", "4", 80000, 1));
    }

    @Test
    public void addExistingEmployee() {
        service.addEmployee("Роман", "Давыдов", 80000, 1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> service.addEmployee("Роман", "Давыдов", 80000, 1));
    }

    @Test
    public void findEmployee() {
        service.addEmployee("Роман", "Давыдов", 80000, 1);
        Employee employee = service.findEmployee("Роман", "Давыдов", 80000, 1);
        assertEquals("Роман", employee.getFirstName());
        assertEquals("Давыдов", employee.getLastName());
        assertEquals(80000, employee.getSalary());
        assertEquals(1, employee.getDepartment());
    }

    @Test
    public void findNotExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Роман", "Давыдов", 80000, 1));
    }

    @Test
    public void deleteEmployee() {
        service.addEmployee("Роман", "Давыдов", 80000, 1);
        service.deleteEmployee("Роман", "Давыдов", 80000, 1);
        assertThrows(EmployeeNotFoundException.class, () -> service.deleteEmployee("Роман", "Давыдов", 80000, 1));
    }

    @Test
    public void deleteNotExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> service.deleteEmployee("Роман", "Давыдов", 80000, 1));
    }

    @Test
    public void returnAllEmployees() {
        Employee davydov = service.addEmployee("Роман", "Давыдов", 80000, 1);
        Employee zaiceva = service.addEmployee("Анна", "Зайцева", 70000, 1);
        Collection<Employee> employees = service.findAll();
        assertEquals(2, employees.size());
        MatcherAssert.assertThat(employees, Matchers.containsInAnyOrder(davydov, zaiceva));
    }
}

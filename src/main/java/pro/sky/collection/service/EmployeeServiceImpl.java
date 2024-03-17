package pro.sky.collection.service;

import org.springframework.stereotype.Service;
import pro.sky.collection.Employee;
import pro.sky.collection.exception.EmployeeAlreadyAddedException;
import pro.sky.collection.exception.EmployeeNotFoundException;
import pro.sky.collection.exception.EmployeeStorageIsFullException;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    List<Employee> employees = new ArrayList<>();
    private static final int maxCountEmployees = 50;
    @Override
    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName);
        if(employees.size() > maxCountEmployees) {
            throw new EmployeeStorageIsFullException("Коллекция уже заполнена");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже добавлен");
        }
        employees.add(employee);
        return employee;
    }

   @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            employees.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник с таким именем не найден");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if(!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Сотрудник с таким именем не найден");
        }
        return employee;
    }

    public Collection<Employee> findAll() {
        return employees;
    }
}

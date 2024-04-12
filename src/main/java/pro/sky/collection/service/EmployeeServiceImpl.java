package pro.sky.collection.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.collection.Employee;
import pro.sky.collection.exception.BadRequestException;
import pro.sky.collection.exception.EmployeeAlreadyAddedException;
import pro.sky.collection.exception.EmployeeNotFoundException;
import pro.sky.collection.exception.EmployeeStorageIsFullException;


import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> storage = new HashMap<>();
    private static final int maxCountEmployees = 50;
    @Override
    public Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) throws EmployeeAlreadyAddedException {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if(storage.size() > maxCountEmployees) {
            throw new EmployeeStorageIsFullException("Коллекция уже заполнена");
        }
        if (storage.containsKey(firstName + lastName)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с таким именем уже добавлен");
        }
        storage.put(firstName + lastName, employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName, Integer salary, Integer department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (storage.containsKey(firstName + lastName + salary + department)) {
            storage.remove(firstName + lastName);
            return employee;
        }
        throw new EmployeeNotFoundException("Сотрудник с таким именем не найден");
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, Integer salary, Integer department) {
        Employee employee = new Employee(firstName, lastName, salary, department);
        if(!storage.containsKey(firstName + lastName)) {
            throw new EmployeeNotFoundException("Сотрудник с таким именем не найден");
        }
        return storage.get(firstName + lastName);
    }

    @Override
    public void validateName(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new BadRequestException("Невалидные символы");
        }
    }

    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(storage.values());
    }
}

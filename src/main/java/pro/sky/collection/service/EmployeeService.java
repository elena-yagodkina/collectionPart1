package pro.sky.collection.service;

import pro.sky.collection.Employee;
import pro.sky.collection.exception.BadRequestException;
import pro.sky.collection.exception.EmployeeAlreadyAddedException;
import pro.sky.collection.exception.EmployeeNotFoundException;
import pro.sky.collection.exception.EmployeeStorageIsFullException;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, Integer salary, Integer department) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    Employee deleteEmployee(String firstName, String lastName, Integer salary, Integer department) throws EmployeeNotFoundException;

    Employee findEmployee(String firstName, String lastName, Integer salary, Integer department) throws EmployeeNotFoundException;

    void validateName(String firstName, String lastName) throws BadRequestException;

    Collection<Employee> findAll();
}

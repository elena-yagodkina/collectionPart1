package pro.sky.collection.service;

import pro.sky.collection.Employee;
import pro.sky.collection.exception.EmployeeAlreadyAddedException;
import pro.sky.collection.exception.EmployeeNotFoundException;
import pro.sky.collection.exception.EmployeeStorageIsFullException;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;

    Employee deleteEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    Collection<Employee> findAll();
}

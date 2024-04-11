package pro.sky.collection.service;

import pro.sky.collection.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee findEmployeeWithMaxSalary(int department);
    Employee findEmployeeWithMinSalary(int department);
    Map<Integer, List<Employee>> findEmployeesByDepartmentSortedByNameSurname();
    Collection<Employee> findEmployeesByDepartmentSortedByNameSurname(int department);
}

package com.example.Spring_Project_1.Service;

import com.example.Spring_Project_1.Entity.Employee;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public void insertEmployee(List<Employee> employee);
    public List<Employee> getAllEmployees();
    public Optional<Employee> fetchById(int employeeId);
    public void removeById(int employeeId);
    public void updateEmployee(Employee employee);
    public Employee getEmployName(String employeeName ) throws NameNotFoundException;
    public List<Employee> getEmployeesByPages(int page,int size);
}

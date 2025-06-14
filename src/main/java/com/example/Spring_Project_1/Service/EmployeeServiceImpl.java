package com.example.Spring_Project_1.Service;

import com.example.Spring_Project_1.Entity.Employee;
import com.example.Spring_Project_1.Repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @Transactional
    @Override
    public void insertEmployee(List<Employee> employee) {
        try{
        for(Employee s:employee){
            employeeRepository.save(s);}}
        catch(Exception e){
            throw new DuplicateKeyException("Duplicate record");
        }

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    @Override
    public Optional<Employee> fetchById(int employeeId) {
        return employeeRepository.fetchById(employeeId);
                //.orElseThrow(() -> new EmployeeNotFoundException("Employee with "+employeeId+" is not Found "));
    }
    @Transactional
    @Override
    public void removeById(int employeeId) {
        if(employeeRepository.existsById(employeeId)) {
            employeeRepository.removeById(employeeId);
        }else{
            throw new EntityNotFoundException("Id is not exists: "+employeeId);
        }

    }
    @Transactional
    @Override
    public void updateEmployee(Employee employee) {
        if(employeeRepository.existsById(employee.getEmployeeId())){
            employeeRepository.save(employee);}
        else{
            throw new EntityNotFoundException("You are updating the id which is not present in the Entity: "+ employee.getEmployeeId());
        }

    }

    @Override
    public Employee getEmployName(String employeeName) throws NameNotFoundException {
        return employeeRepository.getEmployName(employeeName).
                orElseThrow(() -> new NameNotFoundException("Name is not present in the table: " + employeeName));
    }
    @Override
    public List<Employee> getEmployeesByPages(int page,int size){
        size=page*size;
        if(page<0){
            throw new RuntimeException("Please enter the page number greater 0:" +page);
        }
        return employeeRepository.getEmployeesByPages(page,size);
    }




}

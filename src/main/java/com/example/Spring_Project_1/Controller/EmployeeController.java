package com.example.Spring_Project_1.Controller;

import com.example.Spring_Project_1.CustomException.EmployeeNotFoundException;
import com.example.Spring_Project_1.Entity.Employee;
import com.example.Spring_Project_1.Service.EmployeeService;
import com.sun.net.httpserver.Headers;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //To add Employees records into Table
    @PostMapping()
    public ResponseEntity<String> addEmployee(@RequestBody List<Employee> employee){
        employeeService.insertEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //To get all records in the table
    @GetMapping("/all")
    public ResponseEntity<List<Employee>> allEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    //To get the employee records by using Employee ID
    @GetMapping("{employeeId}")
    public ResponseEntity<Optional<Employee>> getOneEmployeeById(@PathVariable("employeeId") int employeeId){
        //return new ResponseEntity<>(employeeService.fetchById(employeeId),HttpStatus.OK);
        return ResponseEntity.ok(employeeService.fetchById(employeeId));
    }

    //To update the records in the table
    @PostMapping("update")
    public ResponseEntity<String> updateEmployee(@RequestBody Employee employee){
        employeeService.updateEmployee(employee);
        //return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.ok("Updated");
    }

    //To delete the records in the table
    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> removeOneEmployee(@PathVariable int employeeId){
        employeeService.removeById(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //To get the employee records by using Employee Name
   @GetMapping("EmployeeName/{employeeName}")
    public ResponseEntity<Employee> getOneEmployeeByName(@PathVariable String employeeName) throws NameNotFoundException {
        return new ResponseEntity<>(employeeService.getEmployName(employeeName),HttpStatus.OK);
    }

    //To get the employee records by pages
    @GetMapping()
    public ResponseEntity<List<Employee>> getAllEmployeeRecords(@RequestParam(required = false) Integer page,
                                                                 @RequestParam(required = false) Integer size){
        List<Employee> employeeList;
        if(page!=null && size!=null){
            employeeList=employeeService.getEmployeesByPages(size,page);}
        else{
            employeeList=employeeService.getAllEmployees();
        }
        return new ResponseEntity<>(employeeList,HttpStatus.OK);
    }


}

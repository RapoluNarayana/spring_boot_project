package com.example.Spring_Project_1.Repository;

import com.example.Spring_Project_1.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query(value="SELECT * FROM employee_table",nativeQuery = true)
    public List<Employee> getAllEmployees();

    @Query(value = "SELECT * FROM employee_table e WHERE e.employee_name = :name", nativeQuery = true)
    public Optional<Employee> getEmployName(@Param("name") String employeeName);
    @Query(value="SELECT * FROM employee_table LIMIT :size OFFSET :page",nativeQuery = true)
    public List<Employee> getEmployeesByPages(@Param("size") int size,@Param("page") int page);

    @Modifying
    @Query(name = "removeById")
    public void removeById(@Param("employeeId") Integer employeeId);
    @Query(name="fetchById")
    public Optional<Employee> fetchById(@Param("employeeId") Integer employeeId);



}

package com.example.mySQL.repository;

import com.example.mySQL.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository <Employee, Integer> {

    Optional<Employee> findByApiKey(String apiKey);

}

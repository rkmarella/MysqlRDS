package com.example.mySQL.service;

import com.example.mySQL.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {

    public Employee createEmp(Employee emp);
    public List<Employee> getEmployee();
    public Employee getEmployee(int id);
    public Employee updateEmp(Employee emp,int id);
    public void deleteEmp(int id);
}

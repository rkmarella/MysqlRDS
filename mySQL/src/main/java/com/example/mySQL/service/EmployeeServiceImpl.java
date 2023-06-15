package com.example.mySQL.service;

import com.example.mySQL.model.Employee;
import com.example.mySQL.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
@Autowired
    EmployeeRepository repo;
    public Employee createEmp(Employee emp) {
      return repo.save(emp);
    }


    public List<Employee> getEmployee() {
       return repo.findAll();
    }


    public Employee getEmployee(int id) {
        return repo.findById(id).orElse(null);
    }


    public Employee updateEmp(Employee emp, int id) {
        Employee oldEmp=repo.findById(id).orElse(null);
        oldEmp.setId(emp.getId());
        oldEmp.setName(emp.getName());
        oldEmp.setSalary(emp.getSalary());
        oldEmp.setMail(emp.getMail());
        repo.save(oldEmp);
        return oldEmp;
    }

@Override
    public void deleteEmp(int id) {
        repo.deleteById(id);
    }
}

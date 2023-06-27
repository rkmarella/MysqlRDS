package com.example.mySQL.controller;

import com.example.mySQL.model.Employee;
import com.example.mySQL.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @PostMapping("/save")
    public Employee saveEmployee( @RequestBody Employee emp){
        return service.createEmp(emp);
    }
@GetMapping("/getemps")
    public List<Employee> getEmps(){
        return service.getEmployee();
    }
@GetMapping("/getemp/{id}")
    public Employee getEmployee(@PathVariable int id){
       return service.getEmployee(id);
    }
@PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id,@RequestBody Employee emp){
        return service.updateEmp(emp,id);
    }

@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        service.deleteEmp(id);
return new ResponseEntity<String>("Employee Deleted", HttpStatus.OK);
    }
}

package com.example.mySQL.controller;

import com.example.mySQL.model.Employee;
import com.example.mySQL.service.EmployeeService;
import com.example.mySQL.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService service;
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    RestTemplate restTemplate;

    private final String otherServiceUrl = "http://localhost:8090/emps/get";
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

    @GetMapping("/api")
    public ResponseEntity<String> callExternalAPI () {
        String response = employeeService.callExternalAPI();
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/external-api")
//    public ResponseEntity<String> callExternal(@RequestBody Employee employeeDTO) {
//        String response = employeeService.callExternal(employeeDTO.getId(), employeeDTO.getName(),
//                employeeDTO.getSalary(), employeeDTO.getMail());
//        return ResponseEntity.ok(response);
//    }
}

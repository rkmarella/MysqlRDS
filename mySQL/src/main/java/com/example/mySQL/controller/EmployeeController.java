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
public class  EmployeeController {
    @Autowired
    EmployeeService service;


    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/save")
    public ResponseEntity<?> saveEmployee(@RequestBody Employee emp){
        try {
            return new ResponseEntity<>(service.createEmp(emp), HttpStatus.CREATED);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getemps")
    public ResponseEntity<?> getEmps(@RequestParam String apiKey){
        if(!service.isValidApiKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(service.getEmployee(), HttpStatus.OK);
    }


    @GetMapping("/getemp/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable int id, @RequestParam String apiKey){
        if(!service.isValidApiKey(apiKey)) {
            return new ResponseEntity<>("Invalid API Key", HttpStatus.UNAUTHORIZED);
        }
        try {
            return new ResponseEntity<>(service.getEmployee(id), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable int id, @RequestBody Employee emp){
        try {
            return new ResponseEntity<>(service.updateEmp(emp,id), HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id){
        try {
            service.deleteEmp(id);
            return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
        } catch(IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api")
    public ResponseEntity<String> callExternalAPI () {
        String response = service.callExternalAPI();
        return ResponseEntity.ok(response);
    }
}

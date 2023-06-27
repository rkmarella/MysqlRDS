package com.example.mySQL.service;

import com.example.mySQL.model.Employee;
import com.example.mySQL.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService{
@Autowired
    EmployeeRepository repo;
    @Autowired
    RestTemplate restTemplate;

    public EmployeeServiceImpl(EmployeeRepository repo, RestTemplate restTemplate) {
    }


    public Employee createEmp(Employee emp) {
        if (isValid(emp)) {
            return repo.save(emp);
        } else {
            throw new IllegalArgumentException("Invalid input data");
        }
    }


    public List<Employee> getEmployee() {
       return repo.findAll();
    }


    public Employee getEmployee(int id) {
        return repo.findById(id).orElse(null);
    }


    public Employee updateEmp(Employee emp, int id) {
        if (isValid(emp)){
            Employee oldEmp=repo.findById(id).orElse(null);
            oldEmp.setId(emp.getId());
            oldEmp.setName(emp.getName());
            oldEmp.setSalary(emp.getSalary());
            oldEmp.setMail(emp.getMail());
            repo.save(oldEmp);
            return oldEmp;
        }else{
            throw new IllegalArgumentException("Invalid input data");
        }
    }



@Override
    public void deleteEmp(int id) {
        repo.deleteById(id);
    }

    // Method to validate the input data
    private boolean isValid(Employee emp) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (emp == null)
            return false;
        return pat.matcher(emp.getMail()).matches();
    }
    // Method to call an external API
    public String callExternalAPI() {
        String apiUrl = "http://localhost:8080/emp/getemps"; // replace with your API URL
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        return response.getBody();
    }

//    public String callExternal(int id, String name, double salary, String mail) {
//        String apiUrl = "http://localhost:8080/emp/save"; // replace with your API URL
//        Map<String, Object> bodyMap = new HashMap<>();
//        bodyMap.put("id", id);
//        bodyMap.put("name", name);
//        bodyMap.put("salary", salary);
//        bodyMap.put("mail", mail);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonBody = null;
//        try {
//            jsonBody = objectMapper.writeValueAsString(bodyMap);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
//        ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.PUT, request, String.class);
//        return response.getBody();
//    }

}

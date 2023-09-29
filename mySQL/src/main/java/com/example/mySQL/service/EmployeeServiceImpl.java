package com.example.mySQL.service;

import com.example.mySQL.Rules.EmployeeValidator;
import com.example.mySQL.model.Employee;
import com.example.mySQL.repository.EmployeeRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repo;
    private final RestTemplate restTemplate;
    private final EmployeeValidator employeeValidator;



    public EmployeeServiceImpl(EmployeeRepository repo, RestTemplate restTemplate, EmployeeValidator employeeValidator) {
        this.repo = repo;
        this.restTemplate = restTemplate;
        this.employeeValidator = employeeValidator;
    }


    @Override
    public Employee createEmp(Employee emp) {
        if (emp == null) {
            throw new IllegalArgumentException("Employee object is null");
        } else if (!employeeValidator.isValidEmail(emp.getMail())) {
            throw new IllegalArgumentException("Email format is incorrect");
        } else if (!employeeValidator.isValidPassword(emp.getPassword())) {
            throw new IllegalArgumentException("Password is invalid");
        }

        return repo.save(emp);
    }

    @Override
    public List<Employee> getEmployee() {
        return repo.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmp(Employee emp, int id) {
        if (emp == null) {
            throw new IllegalArgumentException("Employee object is null");
        } else if (!employeeValidator.isValidEmail(emp.getMail())) {
            throw new IllegalArgumentException("Email format is incorrect");
        } else if (!employeeValidator.isValidPassword(emp.getPassword())) {
            throw new IllegalArgumentException("Password is invalid");
        }

        Optional<Employee> oldEmpOptional = repo.findById(id);
        if (oldEmpOptional.isEmpty()) {
            throw new IllegalArgumentException("Employee with id " + id + " not found");
        }
        Employee oldEmp = oldEmpOptional.get();
        oldEmp.setId(emp.getId());
        oldEmp.setName(emp.getName());
        oldEmp.setSalary(emp.getSalary());
        oldEmp.setMail(emp.getMail());
        oldEmp.setPassword(emp.getPassword()); // Assuming Employee has a password field
        return repo.save(oldEmp);
    }



    @Override
    public void deleteEmp(int id) {
        repo.deleteById(id);
    }




    @Override
    public String callExternalAPI() {
        String apiUrl = "http://localhost:8080/emp/getemps"; // replace with your API URL
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Failed to call API at " + apiUrl, e);
        }
    }
    @Override
    public boolean isValidApiKey(String apiKey) {
        return repo.findByApiKey(apiKey).isPresent();
    }

}

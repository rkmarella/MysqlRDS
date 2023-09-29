//package com.example.mySQL;
//
//
//
//import com.example.mySQL.controller.EmployeeController;
//import com.example.mySQL.model.Employee;
//import com.example.mySQL.service.EmployeeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//class EmployeeControllerTest {
//    @Mock
//    private EmployeeService service;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @InjectMocks
//    private   EmployeeController controller;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testSaveEmployee_Success() {
//        // Arrange
//        Employee emp = new Employee();
//        when(service.createEmp(any(Employee.class))).thenReturn(emp);
//
//        // Act
//        ResponseEntity<?> response = controller.saveEmployee(emp);
//
//        // Assert
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(emp, response.getBody());
//        verify(service, times(1)).createEmp(emp);
//    }
//
//    @Test
//    void testSaveEmployee_InvalidRequest() {
//        // Arrange
//        Employee emp = new Employee();
//        String errorMessage = "Invalid request";
//        when(service.createEmp(any(Employee.class))).thenThrow(new IllegalArgumentException(errorMessage));
//
//        // Act
//        ResponseEntity<?> response = controller.saveEmployee(emp);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(errorMessage, response.getBody());
//        verify(service, times(1)).createEmp(emp);
//    }
//
//    @Test
//    void testGetEmps() {
//        // Arrange
//        List<Employee> employees = new ArrayList<>();
//        when(service.getEmployee()).thenReturn(employees);
//
//        // Act
//        List<Employee> result = controller.getEmps();
//
//        // Assert
//        assertEquals(employees, result);
//        verify(service, times(1)).getEmployee();
//    }
//
//    @Test
//    void testGetEmployee_ValidId() {
//        // Arrange
//        int id = 1;
//        Employee emp = new Employee();
//        when(service.getEmployee(id)).thenReturn(emp);
//
//        // Act
//        ResponseEntity<?> response = controller.getEmployee(id);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(emp, response.getBody());
//        verify(service, times(1)).getEmployee(id);
//    }
//
//    @Test
//    void testGetEmployee_InvalidId() {
//        // Arrange
//        int id = 1;
//        String errorMessage = "Employee not found";
//        when(service.getEmployee(id)).thenThrow(new IllegalArgumentException(errorMessage));
//
//        // Act
//        ResponseEntity<?> response = controller.getEmployee(id);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(errorMessage, response.getBody());
//        verify(service, times(1)).getEmployee(id);
//    }
//
//    @Test
//    void testUpdateEmployee_ValidId() {
//        // Arrange
//        int id = 1;
//        Employee emp = new Employee();
//        when(service.updateEmp(any(Employee.class), eq(id))).thenReturn(emp);
//
//        // Act
//        ResponseEntity<?> response = controller.updateEmployee(id, emp);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(emp, response.getBody());
//        verify(service, times(1)).updateEmp(emp, id);
//    }
//
//    @Test
//    void testUpdateEmployee_InvalidId() {
//        // Arrange
//        int id = 1;
//        Employee emp = new Employee();
//        String errorMessage = "Invalid request";
//        when(service.updateEmp(any(Employee.class), eq(id))).thenThrow(new IllegalArgumentException(errorMessage));
//
//        // Act
//        ResponseEntity<?> response = controller.updateEmployee(id, emp);
//
//        // Assert
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals(errorMessage, response.getBody());
//        verify(service, times(1)).updateEmp(emp, id);
//    }
//
//    @Test
//    void testDeleteEmployee_ValidId() {
//        // Arrange
//        int id = 1;
//
//        // Act
//        ResponseEntity<String> response = controller.deleteEmployee(id);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Employee Deleted", response.getBody());
//        verify(service, times(1)).deleteEmp(id);
//    }
//
//    @Test
//    void testDeleteEmployee_InvalidId() {
//        // Arrange
//        int id = 1;
//        String errorMessage = "Employee not found";
//        doThrow(new IllegalArgumentException(errorMessage)).when(service).deleteEmp(id);
//
//        // Act
//        ResponseEntity<String> response = controller.deleteEmployee(id);
//
//        // Assert
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//        assertEquals(errorMessage, response.getBody());
//        verify(service, times(1)).deleteEmp(id);
//    }
//}

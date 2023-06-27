package com.example.mySQL;


import com.example.mySQL.controller.EmployeeController;
import com.example.mySQL.model.Employee;
import com.example.mySQL.service.EmployeeService;
import com.example.mySQL.service.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService service;

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    void testSaveEmployee() throws Exception {
        Employee emp = new Employee();  // Assuming Employee has a default constructor
        given(service.createEmp(any(Employee.class))).willReturn(emp);

        mockMvc.perform(post("/emp/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Put your Employee JSON here
                .andExpect(status().isOk());

        verify(service, times(1)).createEmp(any(Employee.class));
    }

    @Test
    void testGetEmps() throws Exception {
        given(service.getEmployee()).willReturn(Arrays.asList(new Employee(), new Employee()));

        mockMvc.perform(get("/emp/getemps")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getEmployee();
    }

    @Test
    void testGetEmployee() throws Exception {
        Employee emp = new Employee();
        given(service.getEmployee(any(Integer.class))).willReturn(emp);

        mockMvc.perform(get("/emp/getemp/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).getEmployee(any(Integer.class));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee emp = new Employee();
        given(service.updateEmp(any(Employee.class), any(Integer.class))).willReturn(emp);

        mockMvc.perform(put("/emp/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))  // Put your Employee JSON here
                .andExpect(status().isOk());

        verify(service, times(1)).updateEmp(any(Employee.class), any(Integer.class));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        mockMvc.perform(delete("/emp/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(service, times(1)).deleteEmp(any(Integer.class));
    }

    @Test
    void testCallExternalAPI() throws Exception {
        String mockResponse = "Mock API Response";
        given(employeeService.callExternalAPI()).willReturn(mockResponse);

        mockMvc.perform(get("/emp/api")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));

        verify(employeeService, times(1)).callExternalAPI();
    }
}
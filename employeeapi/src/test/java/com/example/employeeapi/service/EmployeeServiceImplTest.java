package com.example.employeeapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.employeeapi.entity.Employee;
import com.example.employeeapi.repository.EmployeeRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void testGetAllEmployees() {
        Employee emp1 = new Employee();
        emp1.setId(1L);
        emp1.setName("Revathi");
        emp1.setRole("Developer");

        Employee emp2 = new Employee();
        emp2.setId(2L);
        emp2.setName("Anu");
        emp2.setRole("Tester");

        when(repository.findAll()).thenReturn(Arrays.asList(emp1, emp2));

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
    }

    @Test
    void testGetEmployeeById() {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setName("Revathi");
        emp.setRole("Developer");

        when(repository.findById(1L)).thenReturn(Optional.of(emp));

        Employee result = employeeService.getEmployeeById(1L);

        assertEquals("Revathi", result.getName());
    }

    @Test
    void testCreateEmployee() {
        Employee emp = new Employee();
        emp.setName("Revathi");
        emp.setRole("Developer");

        when(repository.save(emp)).thenReturn(emp);

        Employee saved = employeeService.createEmployee(emp);

        assertEquals("Developer", saved.getRole());
    }

    @Test
    void testDeleteEmployee() {
        employeeService.deleteEmployee(1L);
        verify(repository).deleteById(1L);
    }
}
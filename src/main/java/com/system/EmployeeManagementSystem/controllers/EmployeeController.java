package com.system.EmployeeManagementSystem.controllers;

import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.models.Employee;
import com.system.EmployeeManagementSystem.services.EmployeeService;
import com.system.EmployeeManagementSystem.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/showAll")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        return employeeService.getById(id);
    }

    @PostMapping("/add")
    public EmployeeDTO addEmployee(@RequestBody EmployeeDTO department) {
        return employeeService.save(department);
    }

    @PutMapping("/update")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO department) {
        return employeeService.update(department);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
    }
}

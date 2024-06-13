package com.system.EmployeeManagementSystem.controllers;

import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
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
    public String addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.save(employeeDTO);
    }

    @PutMapping("/{id}/update")
    public String updateEmployee(@PathVariable int id,@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.update(employeeDTO, id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.delete(id);
    }
}

package com.system.EmployeeManagementSystem.controllers;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.services.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/showAll")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAll();
    }

    @GetMapping("/{id}")
    public DepartmentDTO getDepartmentById(@PathVariable int id) {
        return departmentService.getById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DepartmentDTO addDepartment(@RequestBody DepartmentDTO department) {
        return departmentService.save(department);
    }

    @PutMapping("/update")
    public DepartmentDTO updateDepartment(@RequestBody DepartmentDTO department) {
        return departmentService.update(department);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.delete(id);
    }
}

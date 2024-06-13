package com.system.EmployeeManagementSystem.controllers;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
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
    public String addDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.save(departmentDTO);
    }

    @PutMapping("/{id}/update")
    public String updateDepartment(@PathVariable int id ,@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.update(departmentDTO, id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteDepartment(@PathVariable int id) {
        departmentService.delete(id);
    }
}

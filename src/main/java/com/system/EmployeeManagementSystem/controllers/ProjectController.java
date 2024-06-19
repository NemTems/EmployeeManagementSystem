package com.system.EmployeeManagementSystem.controllers;


import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.services.implementation.ProjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl idCardService) {
        this.projectService = idCardService;
    }

    @GetMapping("/showAll")
    public ResponseEntity<List<ProjectDTO>> getAllProjects(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return new ResponseEntity<>(projectService.getAllProjects(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable int id) {
        return new ResponseEntity<>(projectService.getProjectById(id),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProject(@RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.createProject(projectDTO),HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateProject(@PathVariable int id, @RequestBody ProjectDTO projectDTO) {
        return new ResponseEntity<>(projectService.updateProject(projectDTO, id),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteProject(@PathVariable int id) {
        projectService.deleteProjectById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}

package com.system.EmployeeManagementSystem.controllers;


import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.models.Project;
import com.system.EmployeeManagementSystem.services.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService idCardService) {
        this.projectService = idCardService;
    }

    @GetMapping("/showAll")
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAll();
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable int id) {
        return projectService.getById(id);
    }

    @PostMapping("/add")
    public void addProject(@RequestBody Project department) {
        projectService.save(department);
    }

    @PutMapping("/update")
    public void updateProject(@RequestBody Project department) {
        projectService.update(department);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteProject(@PathVariable int id) {
        projectService.delete(id);
    }
}

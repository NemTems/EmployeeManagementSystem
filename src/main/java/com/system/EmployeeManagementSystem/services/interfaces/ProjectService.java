package com.system.EmployeeManagementSystem.services.interfaces;

import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects(int pageNo, int pageSize);

    ProjectDTO getProjectById(int id);

    String createProject(ProjectDTO projectDTO);

    String updateProject(ProjectDTO projectDTO, int id);

    void deleteProjectById(int id);
}

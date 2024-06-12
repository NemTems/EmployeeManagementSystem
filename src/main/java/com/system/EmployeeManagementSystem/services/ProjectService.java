package com.system.EmployeeManagementSystem.services;

import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.mapper.ProjectDTOMapper;
import com.system.EmployeeManagementSystem.models.Project;
import com.system.EmployeeManagementSystem.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAll() {
        return projectRepository.findAll().stream()
                .map(ProjectDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getById(int id) {
        return projectRepository.findById(id).map(ProjectDTOMapper::toDTO).get();
    }

    public Project save(Project Project) {
        return projectRepository.save(Project);
    }

    public Project update(Project Project) {
        return projectRepository.save(Project);
    }

    public void delete(int id) {
        projectRepository.deleteById(id);
    }

}

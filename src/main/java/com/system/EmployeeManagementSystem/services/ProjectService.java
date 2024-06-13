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

    private final ProjectRepository projectRepository;

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

    public String save(ProjectDTO projectDTO) {
        projectRepository.save(ProjectDTOMapper.toEntity(projectDTO));
        return "Saved";
    }

    public String update(ProjectDTO projectDTO, int id) {
        Project foundProject = projectRepository.findById(id).get();
        foundProject.setName(projectDTO.getName());
        foundProject.setStart_date(projectDTO.getStartDate());
        foundProject.setEnd_date(projectDTO.getEndDate());
        foundProject.setEmployees(projectDTO.getEmployees().stream()
                .map(ProjectDTOMapper::EmployeeToEntity)
                .collect(Collectors.toSet()));
        foundProject.setTasks(projectDTO.getTasks().stream()
                .map(ProjectDTOMapper::TasksToEntity)
                .collect(Collectors.toSet()));

        projectRepository.save(foundProject);
        return "Updated";
    }

    public void delete(int id) {
        projectRepository.deleteById(id);
    }

}

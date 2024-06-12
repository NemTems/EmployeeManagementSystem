package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.models.Task;
import com.system.EmployeeManagementSystem.models.Project;

import java.util.stream.Collectors;

public class TaskDTOMapper {

    public static TaskDTO toDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setProject(TaskDTOMapper.ProjectToDTO(task.getProject()));
        return dto;
    }

    public static Task toEntity(TaskDTO dto) {
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setProject(TaskDTOMapper.ProjectToEntity(dto.getProject()));
        return task;
    }

    public static ProjectDTO ProjectToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setName(project.getName());
        dto.setStartDate(project.getStart_date());
        dto.setEndDate(project.getEnd_date());
        dto.setEmployees(project.getEmployees().stream()
                .map(ProjectDTOMapper::EmployeeToDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static Project ProjectToEntity(ProjectDTO dto) {
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setName(dto.getName());
        project.setStart_date(dto.getStartDate());
        project.setEnd_date(dto.getEndDate());
        project.setEmployees(dto.getEmployees().stream()
                .map(EmployeeDTOMapper::toEntity)
                .collect(Collectors.toSet()));
        return project;
    }
}

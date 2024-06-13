package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.models.Task;
import com.system.EmployeeManagementSystem.models.Project;

import java.util.stream.Collectors;

public class TaskDTOMapper {

    public static TaskDTO toDTO(Task task) {
        if (task == null) return null;

        return TaskDTO.builder()
                .description(task.getDescription())
                .status(task.getStatus())
                .project(ProjectToDTO(task.getProject()))
                .build();
    }

    public static Task toEntity(TaskDTO dto) {
        if (dto == null) return null;

        return Task.builder()
                .description(dto.getDescription())
                .status(dto.getStatus())
                .project(ProjectToEntity(dto.getProject()))
                .build();
    }

    public static ProjectDTO ProjectToDTO(Project project) {
        if (project == null) return null;

        return ProjectDTO.builder()
                .name(project.getName())
                .startDate(project.getStart_date())
                .endDate(project.getEnd_date())
                .employees(project.getEmployees() != null
                        ? project.getEmployees().stream()
                        .map(ProjectDTOMapper::EmployeeToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Project ProjectToEntity(ProjectDTO dto) {
        if (dto == null) return null;

        return Project.builder()
                .name(dto.getName())
                .start_date(dto.getStartDate())
                .end_date(dto.getEndDate())
                .employees(dto.getEmployees() != null
                        ? dto.getEmployees().stream()
                        .map(ProjectDTOMapper::EmployeeToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}

package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.DTOs.TaskDTO;
import com.system.EmployeeManagementSystem.models.Project;
import com.system.EmployeeManagementSystem.models.Employee;
import com.system.EmployeeManagementSystem.models.Task;

import java.util.stream.Collectors;

public class ProjectDTOMapper {

    public static ProjectDTO toDTO(Project project) {
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
                .tasks(project.getTasks() != null
                        ? project.getTasks().stream()
                        .map(ProjectDTOMapper::TasksToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Project toEntity(ProjectDTO dto) {
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
                .tasks(dto.getTasks() != null
                        ? dto.getTasks().stream()
                        .map(ProjectDTOMapper::TasksToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static EmployeeDTO EmployeeToDTO(Employee employee) {
        if (employee == null) return null;

        return EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .department(EmployeeDTOMapper.DepartmentToDTO(employee.getDepartment()))
                .idCard(EmployeeDTOMapper.IDCardToDTO(employee.getIdcard()))
                .projects(employee.getProjects() != null
                        ? employee.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Employee EmployeeToEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(EmployeeDTOMapper.DepartmentToEntity(dto.getDepartment()))
                .idcard(EmployeeDTOMapper.IDCardToEntity(dto.getIdCard()))
                .projects(dto.getProjects() != null
                        ? dto.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static TaskDTO TasksToDTO(Task task) {
        if (task == null) return null;

        return TaskDTO.builder()
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }

    public static Task TasksToEntity(TaskDTO dto) {
        if (dto == null) return null;

        return Task.builder()
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }
}

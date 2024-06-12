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
        ProjectDTO dto = new ProjectDTO();
        dto.setName(project.getName());
        dto.setStartDate(project.getStart_date());
        dto.setEndDate(project.getEnd_date());
        dto.setEmployees(project.getEmployees().stream()
                .map(ProjectDTOMapper::EmployeeToDTO)
                .collect(Collectors.toSet()));
        dto.setTasks(project.getTasks().stream()
                .map(ProjectDTOMapper::TasksToDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static Project toEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setStart_date(dto.getStartDate());
        project.setEnd_date(dto.getEndDate());
        project.setEmployees(dto.getEmployees().stream()
                .map(ProjectDTOMapper::EmployeeToEntity)
                .collect(Collectors.toSet()));
        project.setTasks(dto.getTasks().stream()
                .map(ProjectDTOMapper::TasksToEntity)
                .collect(Collectors.toSet()));
        return project;
    }

    public static EmployeeDTO EmployeeToDTO(Employee employee) {

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDepartment(EmployeeDTOMapper.DepartmentToDTO(employee.getDepartment()));
        dto.setIdCard(EmployeeDTOMapper.IDCardToDTO(employee.getIdcard()));
        dto.setProjects(employee.getProjects().stream()
                .map(EmployeeDTOMapper::ProjectToDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static Employee EmployeeToEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(EmployeeDTOMapper.DepartmentToEntity(dto.getDepartment()));
        employee.setIdcard(EmployeeDTOMapper.IDCardToEntity(dto.getIdCard()));
        return employee;
    }

    public static TaskDTO TasksToDTO(Task task) {
        TaskDTO dto = new TaskDTO();
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        return dto;
    }

    public static Task TasksToEntity(TaskDTO dto) {
        Task task = new Task();
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        return task;
    }
}

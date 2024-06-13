package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.DTOs.ProjectDTO;
import com.system.EmployeeManagementSystem.models.Employee;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.models.Project;

import java.util.stream.Collectors;

public class EmployeeDTOMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee == null) return null;

        return EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .department(DepartmentToDTO(employee.getDepartment()))
                .idCard(IDCardToDTO(employee.getIdcard()))
                .projects(employee.getProjects() != null
                        ? employee.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(DepartmentToEntity(dto.getDepartment()))
                .idcard(IDCardToEntity(dto.getIdCard()))
                .projects(dto.getProjects() != null
                        ? dto.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static DepartmentDTO DepartmentToDTO(Department department) {
        if (department == null) return null;

        return DepartmentDTO.builder()
                .name(department.getName())
                .location(department.getLocation())
                .build();
    }

    public static Department DepartmentToEntity(DepartmentDTO dto) {
        if (dto == null) return null;

        return Department.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .build();
    }

    public static IDCardDTO IDCardToDTO(IDCard idCard) {
        if (idCard == null) return null;

        return IDCardDTO.builder()
                .issueDate(idCard.getIssue_date())
                .expiryDate(idCard.getExpiry_date())
                .build();
    }

    public static IDCard IDCardToEntity(IDCardDTO dto) {
        if (dto == null) return null;

        return IDCard.builder()
                .issue_date(dto.getIssueDate())
                .expiry_date(dto.getExpiryDate())
                .build();
    }

    public static ProjectDTO ProjectToDTO(Project project) {
        if (project == null) return null;

        return ProjectDTO.builder()
                .name(project.getName())
                .startDate(project.getStart_date())
                .endDate(project.getEnd_date())
                .tasks(project.getTasks() != null
                        ? project.getTasks().stream()
                        .map(ProjectDTOMapper::TasksToDTO)
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
                .tasks(dto.getTasks() != null
                        ? dto.getTasks().stream()
                        .map(ProjectDTOMapper::TasksToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}

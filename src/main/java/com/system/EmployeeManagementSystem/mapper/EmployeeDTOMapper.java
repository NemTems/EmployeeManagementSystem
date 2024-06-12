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

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDepartment(DepartmentToDTO(employee.getDepartment()));
        dto.setIdCard(IDCardToDTO(employee.getIdcard()));
        if (employee.getProjects() != null) {
            dto.setProjects(employee.getProjects().stream()
                    .map(EmployeeDTOMapper::ProjectToDTO)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto) {
        if (dto == null) return null;

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(DepartmentToEntity(dto.getDepartment()));
        employee.setIdcard(IDCardToEntity(dto.getIdCard()));
        if (dto.getProjects() != null) {
            employee.setProjects(dto.getProjects().stream()
                    .map(EmployeeDTOMapper::ProjectToEntity)
                    .collect(Collectors.toSet()));
        }
        return employee;
    }

    public static DepartmentDTO DepartmentToDTO(Department department) {
        if (department == null) return null;

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());
        return dto;
    }

    public static Department DepartmentToEntity(DepartmentDTO dto) {
        if (dto == null) return null;

        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        return department;
    }

    public static IDCardDTO IDCardToDTO(IDCard idCard) {
        if (idCard == null) return null;

        IDCardDTO dto = new IDCardDTO();
        dto.setIssueDate(idCard.getIssue_date());
        dto.setExpiryDate(idCard.getExpiry_date());
        return dto;
    }

    public static IDCard IDCardToEntity(IDCardDTO dto) {
        if (dto == null) return null;

        IDCard idCard = new IDCard();
        idCard.setIssue_date(dto.getIssueDate());
        idCard.setExpiry_date(dto.getExpiryDate());
        return idCard;
    }

    public static ProjectDTO ProjectToDTO(Project project) {
        if (project == null) return null;

        ProjectDTO dto = new ProjectDTO();
        dto.setName(project.getName());
        dto.setStartDate(project.getStart_date());
        dto.setEndDate(project.getEnd_date());
        if (project.getTasks() != null) {
            dto.setTasks(project.getTasks().stream()
                    .map(ProjectDTOMapper::TasksToDTO)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Project ProjectToEntity(ProjectDTO dto) {
        if (dto == null) return null;

        Project project = new Project();
        project.setName(dto.getName());
        project.setStart_date(dto.getStartDate());
        project.setEnd_date(dto.getEndDate());
        if (dto.getTasks() != null) {
            project.setTasks(dto.getTasks().stream()
                    .map(ProjectDTOMapper::TasksToEntity)
                    .collect(Collectors.toSet()));
        }
        return project;
    }
}

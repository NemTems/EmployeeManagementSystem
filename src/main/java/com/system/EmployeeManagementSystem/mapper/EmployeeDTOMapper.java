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
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDepartment(EmployeeDTOMapper.DepartmentToDTO(employee.getDepartment())); //done
        dto.setIdCard(EmployeeDTOMapper.IDCardToDTO(employee.getIdcard())); //
        dto.setProjects(employee.getProjects().stream()
                .map(EmployeeDTOMapper::ProjectToDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static Employee toEntity(EmployeeDTO dto) {
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(EmployeeDTOMapper.DepartmentToEntity(dto.getDepartment()));
        employee.setIdcard(EmployeeDTOMapper.IDCardToEntity(dto.getIdCard()));
        employee.setProjects(dto.getProjects().stream()
                .map(EmployeeDTOMapper::ProjectToEntity)
                .collect(Collectors.toSet()));
        return employee;
    }

    public static DepartmentDTO DepartmentToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());
        return dto;
    }

    public static Department DepartmentToEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        return department;
    }

    public static IDCardDTO IDCardToDTO(IDCard idCard) {
        IDCardDTO dto = new IDCardDTO();
        dto.setIssueDate(idCard.getIssue_date());
        dto.setExpiryDate(idCard.getExpiry_date());
        return dto;
    }

    public static IDCard IDCardToEntity(IDCardDTO dto) {
        IDCard idCard = new IDCard();
        idCard.setIssue_date(dto.getIssueDate());
        idCard.setExpiry_date(dto.getExpiryDate());
        return idCard;
    }

    public static ProjectDTO ProjectToDTO(Project project) {
        ProjectDTO dto = new ProjectDTO();
        dto.setName(project.getName());
        dto.setStartDate(project.getStart_date());
        dto.setEndDate(project.getEnd_date());
        dto.setTasks(project.getTasks().stream()
                .map(ProjectDTOMapper::TasksToDTO)
                .collect(Collectors.toSet()));
        return dto;
    }

    public static Project ProjectToEntity(ProjectDTO dto) {
        Project project = new Project();
        project.setName(dto.getName());
        project.setStart_date(dto.getStartDate());
        project.setEnd_date(dto.getEndDate());
        project.setTasks(dto.getTasks().stream()
                .map(ProjectDTOMapper::TasksToEntity)
                .collect(Collectors.toSet()));
        return project;
    }
}

package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.models.Employee;

import java.util.stream.Collectors;

public class IDCardDTOMapper {

    public static IDCardDTO toDTO(IDCard idCard) {
        if (idCard == null) return null;

        return IDCardDTO.builder()
                .issueDate(idCard.getIssue_date())
                .expiryDate(idCard.getExpiry_date())
                .employee(IDCardDTOMapper.EmployeeToDTO(idCard.getEmployee()))
                .build();
    }

    public static IDCard toEntity(IDCardDTO dto) {
        if(dto == null) return null;

        return IDCard.builder()
                .issue_date(dto.getIssueDate())
                .expiry_date(dto.getExpiryDate())
                .employee(IDCardDTOMapper.EmployeeToEntity(dto.getEmployee()))
                .build();
    }

    public static EmployeeDTO EmployeeToDTO(Employee employee) {
        if(employee == null) return null;

        return EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .department(EmployeeDTOMapper.DepartmentToDTO(employee.getDepartment()))
                .projects(employee.getProjects() != null
                        ? employee.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Employee EmployeeToEntity(EmployeeDTO dto) {
        if(dto == null) return null;

        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .department(EmployeeDTOMapper.DepartmentToEntity(dto.getDepartment()))
                .projects(dto.getProjects() != null
                        ? dto.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}

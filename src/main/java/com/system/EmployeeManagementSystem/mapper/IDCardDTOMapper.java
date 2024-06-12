package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.IDCardDTO;
import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.models.Employee;

import java.util.stream.Collectors;

public class IDCardDTOMapper {

    public static IDCardDTO toDTO(IDCard idCard) {
        IDCardDTO dto = new IDCardDTO();
        dto.setIssueDate(idCard.getIssue_date());
        dto.setExpiryDate(idCard.getExpiry_date());
        dto.setEmployee(IDCardDTOMapper.EmployeeToDTO(idCard.getEmployee()));
        return dto;
    }

    public static IDCard toEntity(IDCardDTO dto) {
        if(dto == null) return null;

        IDCard idCard = new IDCard();
        idCard.setIssue_date(dto.getIssueDate());
        idCard.setExpiry_date(dto.getExpiryDate());
        idCard.setEmployee(IDCardDTOMapper.EmployeeToEntity(dto.getEmployee()));
        return idCard;
    }

    public static EmployeeDTO EmployeeToDTO(Employee employee) {
        if(employee == null) return null;

        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setDepartment(EmployeeDTOMapper.DepartmentToDTO(employee.getDepartment()));
        if(employee.getProjects() != null) {
            dto.setProjects(employee.getProjects().stream()
                    .map(EmployeeDTOMapper::ProjectToDTO)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Employee EmployeeToEntity(EmployeeDTO dto) {
        if(dto == null) return null;
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(EmployeeDTOMapper.DepartmentToEntity(dto.getDepartment()));
        if(employee.getProjects() != null) {
            employee.setProjects(dto.getProjects().stream()
                    .map(EmployeeDTOMapper::ProjectToEntity)
                    .collect(Collectors.toSet()));
        }
        return employee;
    }
}

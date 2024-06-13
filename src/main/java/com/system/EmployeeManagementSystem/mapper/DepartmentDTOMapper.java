package com.system.EmployeeManagementSystem.mapper;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.models.Employee;

import java.util.stream.Collectors;

public class DepartmentDTOMapper {

    public static DepartmentDTO toDTO(Department department) {
        if (department == null) {
            return null;
        }

        return DepartmentDTO.builder()
                .name(department.getName())
                .location(department.getLocation())
                .employees(department.getEmployees() != null
                        ? department.getEmployees().stream()
                        .map(DepartmentDTOMapper::EmployeeToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        return Department.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .employees(dto.getEmployees() != null
                        ? dto.getEmployees().stream()
                        .map(DepartmentDTOMapper::EmployeeToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static EmployeeDTO EmployeeToDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        return EmployeeDTO.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .idCard(EmployeeDTOMapper.IDCardToDTO(employee.getIdcard()))
                .projects(employee.getProjects() != null
                        ? employee.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToDTO)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }

    public static Employee EmployeeToEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        return Employee.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .idcard(EmployeeDTOMapper.IDCardToEntity(dto.getIdCard()))
                .projects(dto.getProjects() != null
                        ? dto.getProjects().stream()
                        .map(EmployeeDTOMapper::ProjectToEntity)
                        .collect(Collectors.toSet())
                        : null)
                .build();
    }
}

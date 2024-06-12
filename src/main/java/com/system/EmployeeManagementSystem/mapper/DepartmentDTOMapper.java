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

        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());
        if (department.getEmployees() != null) {
            dto.setEmployees(department.getEmployees().stream()
                    .map(DepartmentDTOMapper::EmployeeToDTO)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Department toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        if (dto.getEmployees() != null) {
            department.setEmployees(dto.getEmployees().stream()
                    .map(DepartmentDTOMapper::EmployeeToEntity)
                    .collect(Collectors.toSet()));
        }
        return department;
    }

    public static EmployeeDTO EmployeeToDTO(Employee employee) {
        if (employee == null) {
            return null;
        }
        EmployeeDTO dto = new EmployeeDTO();
        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setIdCard(EmployeeDTOMapper.IDCardToDTO(employee.getIdcard()));
        if (employee.getProjects() != null) {
            dto.setProjects(employee.getProjects().stream()
                    .map(EmployeeDTOMapper::ProjectToDTO)
                    .collect(Collectors.toSet()));
        }
        return dto;
    }

    public static Employee EmployeeToEntity(EmployeeDTO dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setIdcard(EmployeeDTOMapper.IDCardToEntity(dto.getIdCard()));
        if (dto.getProjects() != null) {
            employee.setProjects(dto.getProjects().stream()
                    .map(EmployeeDTOMapper::ProjectToEntity)
                    .collect(Collectors.toSet()));
        }
        return employee;
    }
}

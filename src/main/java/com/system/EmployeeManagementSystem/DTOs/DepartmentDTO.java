package com.system.EmployeeManagementSystem.DTOs;

import com.system.EmployeeManagementSystem.models.Employee;
import lombok.Data;

import java.util.Set;

@Data
public class DepartmentDTO {
    private String name;
    private String location;
    private Set<EmployeeDTO> employees;
}
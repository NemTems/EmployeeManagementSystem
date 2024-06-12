package com.system.EmployeeManagementSystem.DTOs;

import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.models.IDCard;
import com.system.EmployeeManagementSystem.models.Project;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
public class EmployeeDTO {
    private String name;
    private String email;
    private String phone;
    private DepartmentDTO department;
    private IDCardDTO idCard;
    private Set<ProjectDTO> projects;
}
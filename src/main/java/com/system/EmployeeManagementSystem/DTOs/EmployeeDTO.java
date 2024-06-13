package com.system.EmployeeManagementSystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private String name;
    private String email;
    private String phone;
    private DepartmentDTO department;
    private IDCardDTO idCard;
    private Set<ProjectDTO> projects;
}
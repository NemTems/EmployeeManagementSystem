package com.system.EmployeeManagementSystem.DTOs;

import com.system.EmployeeManagementSystem.models.Employee;
import com.system.EmployeeManagementSystem.models.Task;
import lombok.Data;

import java.util.Set;

@Data
public class ProjectDTO {
    private String name;
    private String startDate;
    private String endDate;
    private Set<EmployeeDTO> employees;
    private Set<TaskDTO> tasks;
}


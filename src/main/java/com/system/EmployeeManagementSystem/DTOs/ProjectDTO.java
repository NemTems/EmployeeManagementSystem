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
public class ProjectDTO {
    private String name;
    private String startDate;
    private String endDate;
    private Set<EmployeeDTO> employees;
    private Set<TaskDTO> tasks;
}


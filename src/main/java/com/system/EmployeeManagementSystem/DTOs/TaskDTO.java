package com.system.EmployeeManagementSystem.DTOs;

import com.system.EmployeeManagementSystem.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private String description;
    private TaskStatus status;
    private ProjectDTO project;
}

package com.system.EmployeeManagementSystem.DTOs;

import com.system.EmployeeManagementSystem.TaskStatus;
import com.system.EmployeeManagementSystem.models.Project;
import lombok.Data;

@Data
public class TaskDTO {
    private String description;
    private TaskStatus status;
    private ProjectDTO project;
}

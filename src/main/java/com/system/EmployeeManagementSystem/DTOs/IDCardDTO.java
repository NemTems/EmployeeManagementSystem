package com.system.EmployeeManagementSystem.DTOs;

import com.system.EmployeeManagementSystem.models.Employee;
import lombok.Data;

@Data
public class IDCardDTO {
    private String issueDate;
    private String expiryDate;
    private EmployeeDTO employee;
}
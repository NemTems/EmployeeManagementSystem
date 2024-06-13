package com.system.EmployeeManagementSystem.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IDCardDTO {
    private String issueDate;
    private String expiryDate;
    private EmployeeDTO employee;
}
package com.system.EmployeeManagementSystem.services.interfaces;

import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees(int pageNo, int pageSize);

    EmployeeDTO getEmployeeById(int id);

    String createEmployee(EmployeeDTO employee);

    String updateEmployee(EmployeeDTO employee, int id);

    void deleteEmployeeById(int id);
}

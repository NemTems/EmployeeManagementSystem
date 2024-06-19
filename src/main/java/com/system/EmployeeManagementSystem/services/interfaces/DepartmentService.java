package com.system.EmployeeManagementSystem.services.interfaces;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;


import java.util.List;

public interface DepartmentService {
    List<DepartmentDTO> getAllDepartments(int pageNo, int pageSize);

    DepartmentDTO getDepartmentById(int id);

    String createDepartment(DepartmentDTO department);

    String updateDepartment(DepartmentDTO department, int id);

    void deleteDepartmentById(int id);
}

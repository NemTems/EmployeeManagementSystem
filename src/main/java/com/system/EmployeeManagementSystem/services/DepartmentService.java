package com.system.EmployeeManagementSystem.services;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.mapper.DepartmentDTOMapper;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<DepartmentDTO> getAll() {
        return departmentRepository.findAll().stream()
                .map(DepartmentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO getById(int id) {
        return departmentRepository.findById(id).map(DepartmentDTOMapper::toDTO).get();
    }

    public String save(DepartmentDTO department) {
        departmentRepository.save(DepartmentDTOMapper.toEntity(department));
        return "Saved";
    }

    public String update(DepartmentDTO department, int id) {
        Department foundDepartment = departmentRepository.findById(id).get();

        foundDepartment.setName(department.getName());
        foundDepartment.setLocation(department.getLocation());
        foundDepartment.setEmployees(department.getEmployees().stream()
                .map(DepartmentDTOMapper::EmployeeToEntity)
                .collect(Collectors.toSet()));

        departmentRepository.save(foundDepartment);

        return "Updated";
    }

    public void delete(int id) {
        departmentRepository.deleteById(id);
    }

}

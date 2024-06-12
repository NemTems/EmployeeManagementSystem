package com.system.EmployeeManagementSystem.services;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.mapper.DepartmentDTOMapper;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepository;
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

    public DepartmentDTO save(DepartmentDTO department) {
        departmentRepository.save(DepartmentDTOMapper.toEntity(department));
        return department;
    }

    public DepartmentDTO update(DepartmentDTO department) {
        departmentRepository.save(DepartmentDTOMapper.toEntity(department));
        return department;

    }

    public void delete(int id) {
        departmentRepository.deleteById(id);
    }

}

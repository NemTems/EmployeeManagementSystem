package com.system.EmployeeManagementSystem.services;

import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.mapper.EmployeeDTOMapper;
import com.system.EmployeeManagementSystem.models.Employee;
import com.system.EmployeeManagementSystem.repositories.EmployeeRepository;
import com.system.EmployeeManagementSystem.repositories.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll().stream()
                .map(EmployeeDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(int id) {
        return employeeRepository.findById(id).map(EmployeeDTOMapper::toDTO).get();
    }

    public EmployeeDTO save(EmployeeDTO employee) {
        employeeRepository.save(EmployeeDTOMapper.toEntity(employee));
        return employee;
    }

    public EmployeeDTO update(EmployeeDTO employee) {
        employeeRepository.save(EmployeeDTOMapper.toEntity(employee));
        return employee;
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

}

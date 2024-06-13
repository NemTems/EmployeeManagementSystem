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

    private final EmployeeRepository employeeRepository;

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

    public String save(EmployeeDTO employee) {
        employeeRepository.save(EmployeeDTOMapper.toEntity(employee));
        return "Saved";
    }

    public String update(EmployeeDTO employee, int id) {
        Employee foundEmployee = employeeRepository.findById(id).get();

        foundEmployee.setName(employee.getName());
        foundEmployee.setEmail(employee.getEmail());
        foundEmployee.setPhone(employee.getPhone());
        foundEmployee.setDepartment(EmployeeDTOMapper.DepartmentToEntity(employee.getDepartment()));
        foundEmployee.setIdcard(EmployeeDTOMapper.IDCardToEntity(employee.getIdCard()));
        foundEmployee.setProjects(employee.getProjects().stream()
                .map(EmployeeDTOMapper::ProjectToEntity)
                .collect(Collectors.toSet()));

        employeeRepository.save(foundEmployee);

        return "Updated";
    }

    public void delete(int id) {
        employeeRepository.deleteById(id);
    }

}

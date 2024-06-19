package com.system.EmployeeManagementSystem.services.implementation;

import com.system.EmployeeManagementSystem.DTOs.EmployeeDTO;
import com.system.EmployeeManagementSystem.exceptions.APIRequestException;
import com.system.EmployeeManagementSystem.mapper.EmployeeDTOMapper;
import com.system.EmployeeManagementSystem.models.Employee;
import com.system.EmployeeManagementSystem.repositories.EmployeeRepository;
import com.system.EmployeeManagementSystem.services.interfaces.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return employeeRepository.findAll(pageable).getContent().stream()
                .map(EmployeeDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(int id) {
        return employeeRepository.findById(id).map(EmployeeDTOMapper::toDTO)
                .orElseThrow(() -> new APIRequestException("Employee cannot be found by provided id"));
    }

    @Override
    public String createEmployee(EmployeeDTO employee) {
        try {
            employeeRepository.save(EmployeeDTOMapper.toEntity(employee));
            return "Saved";
        }
        catch (Exception e){
            throw new APIRequestException("Employee cannot be created", e);
        }
    }

    @Override
    public String updateEmployee(EmployeeDTO employee, int id) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new APIRequestException("Employee cannot be found by provided id"));

        try {
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
        catch (Exception e){
            throw new APIRequestException("Employee cannot be updated", e);
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        try {
            employeeRepository.deleteById(id);
        }
        catch (Exception e){
            throw new APIRequestException("Employee cannot be deleted or found by provided id", e);
        }
    }

}

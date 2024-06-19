package com.system.EmployeeManagementSystem.services.implementation;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.exceptions.APIRequestException;
import com.system.EmployeeManagementSystem.mapper.DepartmentDTOMapper;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.repositories.DepartmentRepository;
import com.system.EmployeeManagementSystem.services.interfaces.DepartmentService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        return departmentRepository.findAll(pageable).getContent().stream()
                .map(DepartmentDTOMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO getDepartmentById(int id) {
        return departmentRepository.findById(id).map(DepartmentDTOMapper::toDTO)
                .orElseThrow(() -> new APIRequestException("Department cannot be found by provided id"));
    }

    @Override
    public String createDepartment(DepartmentDTO department) {
        try{
            departmentRepository.save(DepartmentDTOMapper.toEntity(department));
            return "Saved";
        }
        catch (Exception e){
            throw new APIRequestException("Department cannot be created", e);
        }

    }

    @Override
    public String updateDepartment(DepartmentDTO department, int id) {
        Department foundDepartment =  departmentRepository.findById(id).orElseThrow(
                () -> new APIRequestException("Department cannot be found by provided id"));

        try {
            foundDepartment.setName(department.getName());
            foundDepartment.setLocation(department.getLocation());
            foundDepartment.setEmployees(department.getEmployees().stream()
                    .map(DepartmentDTOMapper::EmployeeToEntity)
                    .collect(Collectors.toSet()));
            departmentRepository.save(foundDepartment);

            return "Updated";
        }
        catch (Exception e){
            throw new APIRequestException("Department cannot be updated", e);
        }
    }

    @Override
    public void deleteDepartmentById(int id) {
        try {
            departmentRepository.deleteById(id);
        } catch (Exception e){
            throw new APIRequestException("Department cannot be deleted or found by provided id", e);
        }
    }

}

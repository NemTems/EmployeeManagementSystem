package com.system.EmployeeManagementSystem.service;

import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.repositories.DepartmentRepository;
import com.system.EmployeeManagementSystem.services.implementation.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private Department department;
    private DepartmentDTO departmentDTO;

    @BeforeEach
    public void setUp(){
        department = Department.builder()
                .name("Cool department")
                .location("Cool place")
                .employees(Set.of())
                .build();

        departmentDTO = DepartmentDTO.builder()
                .name("Cool DTO department")
                .location("Cool DTO place")
                .employees(Set.of())
                .build();
    }


    @Test
    public void DepartmentGetAllDepartments(){
        List<Department> departments = List.of(department);
        Pageable pageable = PageRequest.of(1, 10);

        Page<Department> departmentPage = new PageImpl<>(departments, pageable, departments.size());

        when(departmentRepository.findAll(pageable)).thenReturn(departmentPage);

        List<DepartmentDTO> departmentList = departmentService.getAllDepartments(1, 10);

        Assertions.assertFalse(departmentList.isEmpty());
    }

    @Test
    public void DepartmentGetById(){
        when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.ofNullable(department));

        DepartmentDTO savedDepartment = departmentService.getDepartmentById(1);

        Assertions.assertEquals(savedDepartment.getName(), department.getName());
    }

    @Test
    public void DepartmentServiceAddDepartment(){
        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        String response = departmentService.createDepartment(departmentDTO);

        Assertions.assertEquals("Saved",response);
    }

    @Test
    public void DepartmentUpdateById(){

        when(departmentRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(department));
        when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(department);

        String response = departmentService.updateDepartment(departmentDTO,1);

        Assertions.assertEquals("Updated",response);
    }

    @Test
    public void DepartmentDeleteById(){
        doNothing().when(departmentRepository).deleteById(1);

        departmentService.deleteDepartmentById(1);

        verify(departmentRepository, times(1)).deleteById(1);

    }
}

package com.system.EmployeeManagementSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.EmployeeManagementSystem.DTOs.DepartmentDTO;
import com.system.EmployeeManagementSystem.controllers.DepartmentController;
import com.system.EmployeeManagementSystem.services.implementation.DepartmentServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DepartmentControllerTesting {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentServiceImpl departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private DepartmentDTO departmentDTO;

    @BeforeEach
    public void setUp() {

        departmentDTO = DepartmentDTO.builder()
                .name("Cool DTO department")
                .location("Cool DTO place")
                .employees(Set.of())
                .build();
    }

    @Test
    public void DepartmentControllerGetAllDepartments() throws Exception{

        List<DepartmentDTO> departments = List.of(departmentDTO);

        when(departmentService.getAllDepartments(1, 10)).thenReturn(departments);
        ResultActions response = mockMvc.perform(get("/api/departments/showAll")
                .contentType(MediaType.APPLICATION_JSON)
                .param("pageNo", "1")
                .param("pageSize", "10"));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", CoreMatchers.is(departments.getFirst().getName())))
                .andExpect(jsonPath("$[0].location", CoreMatchers.is(departments.getFirst().getLocation())));
    }

    @Test
    public void DepartmentControllerGetDepartmentById() throws Exception{

        when(departmentService.getDepartmentById(1)).thenReturn(departmentDTO);
        ResultActions response = mockMvc.perform(get("/api/departments/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departmentDTO)));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(departmentDTO.getName())))
                .andExpect(jsonPath("$.location", CoreMatchers.is(departmentDTO.getLocation())));
    }

    @Test
    public void DepartmentControllerAddDepartment() throws Exception {
        given(departmentService.createDepartment(ArgumentMatchers.any()))
                .willAnswer((_) -> "Created");

        String departmentJson = objectMapper.writeValueAsString(departmentDTO);

        ResultActions response = mockMvc.perform(post("/api/departments/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(departmentJson)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$", CoreMatchers.is("Created")));
    }

    @Test
    public void DepartmentControllerUpdateDepartmentById() throws Exception{

        when(departmentService.updateDepartment(departmentDTO,1)).thenReturn("Updated");

        ResultActions response = mockMvc.perform(put("/api/departments/1/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(departmentDTO)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$",  CoreMatchers.is("Updated")));
    }

    @Test
    public void DepartmentControllerDeleteDepartmentById() throws Exception{

        doNothing().when(departmentService).deleteDepartmentById(1);

        ResultActions response = mockMvc.perform(delete("/api/departments/1/delete")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk());
    }

}

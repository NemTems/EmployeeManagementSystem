package com.system.EmployeeManagementSystem;

import static org.junit.jupiter.api.Assertions.*;

import com.system.EmployeeManagementSystem.DTOs.*;
import com.system.EmployeeManagementSystem.mapper.DepartmentDTOMapper;
import com.system.EmployeeManagementSystem.mapper.EmployeeDTOMapper;
import com.system.EmployeeManagementSystem.mapper.IDCardDTOMapper;
import com.system.EmployeeManagementSystem.mapper.ProjectDTOMapper;
import com.system.EmployeeManagementSystem.mapper.TaskDTOMapper;
import com.system.EmployeeManagementSystem.models.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Collections;
import java.util.Set;

class MapperTest {

	// CONSTANT INSTANCES FOR TESTING
	private DepartmentDTO departmentDTO;
	private EmployeeDTO employeeDTO;
	private IDCardDTO idCardDTO;
	private ProjectDTO projectDTO;
	private TaskDTO taskDTO;

	private Department departmentEntity;
	private Employee employeeEntity;
	private IDCard idCardEntity;
	private Project projectEntity;
	private Task taskEntity;

	@BeforeEach
	public void setUp() {
		departmentDTO = new DepartmentDTO();
		departmentDTO.setName("Human Resources");
		departmentDTO.setLocation("California");
		departmentDTO.setEmployees(Set.of());

		idCardDTO = new IDCardDTO();
		idCardDTO.setIssueDate("2024-01-01");
		idCardDTO.setExpiryDate("2025-01-01");

		projectDTO = new ProjectDTO();
		projectDTO.setName("Project A");
		projectDTO.setStartDate("2024-06-12");
		projectDTO.setEndDate("2024-12-31");
		projectDTO.setEmployees(Set.of());
		projectDTO.setTasks(Set.of());

		taskDTO = new TaskDTO();
		taskDTO.setDescription("Develop REST API");
		taskDTO.setStatus(TaskStatus.InProgress);

		employeeDTO = new EmployeeDTO();
		employeeDTO.setName("Bobby");
		employeeDTO.setEmail("bobby@gmail.com");
		employeeDTO.setPhone("+371 20 321 425");
		employeeDTO.setDepartment(departmentDTO);
		employeeDTO.setIdCard(idCardDTO);
		employeeDTO.setProjects(Set.of());

		departmentEntity = new Department();
		departmentEntity.setName("Human Resources");
		departmentEntity.setLocation("California");
		departmentEntity.setEmployees(Set.of());

		idCardEntity = new IDCard();
		idCardEntity.setIssue_date("2024-01-01");
		idCardEntity.setExpiry_date("2025-01-01");

		projectEntity = new Project();
		projectEntity.setName("Project A");
		projectEntity.setStart_date("2024-06-12");
		projectEntity.setEnd_date("2024-12-31");
		projectEntity.setEmployees(Set.of());
		projectEntity.setTasks(Set.of());

		taskEntity = new Task();
		taskEntity.setDescription("Develop REST API");
		taskEntity.setStatus(TaskStatus.InProgress);
		taskEntity.setProject(projectEntity);

		employeeEntity = new Employee();
		employeeEntity.setName("Bobby");
		employeeEntity.setEmail("bobby@gmail.com");
		employeeEntity.setPhone("+371 20 321 425");
		employeeEntity.setDepartment(departmentEntity);
		employeeEntity.setIdcard(idCardEntity);
		employeeEntity.setProjects(Set.of());

		idCardEntity.setEmployee(employeeEntity); // IDCard
	}

	@Test
	public void testDepartmentMapping() {
		DepartmentDTO dto = DepartmentDTOMapper.toDTO(departmentEntity);
		assertNotNull(dto);
		assertEquals("Human Resources", dto.getName());
		assertEquals("California", dto.getLocation());
		assertTrue(dto.getEmployees().isEmpty());

		Department entity = DepartmentDTOMapper.toEntity(dto);
		assertNotNull(entity);
		assertEquals("Human Resources", entity.getName());
		assertEquals("California", entity.getLocation());
		assertTrue(entity.getEmployees().isEmpty());
	}

	@Test
	public void testEmployeeMapping() {
		EmployeeDTO dto = EmployeeDTOMapper.toDTO(employeeEntity);
		assertNotNull(dto);
		assertEquals("Bobby", dto.getName());
		assertEquals("bobby@gmail.com", dto.getEmail());
		assertEquals("+371 20 321 425", dto.getPhone());
		assertEquals(departmentDTO.getName(), dto.getDepartment().getName());
		assertEquals(departmentDTO.getLocation(), dto.getDepartment().getLocation());
		assertEquals(idCardDTO.getIssueDate(), dto.getIdCard().getIssueDate());
		assertTrue(dto.getProjects().isEmpty());

		Employee entity = EmployeeDTOMapper.toEntity(dto);
		assertNotNull(entity);
		assertEquals("Bobby", entity.getName());
		assertEquals("bobby@gmail.com", entity.getEmail());
		assertEquals("+371 20 321 425", entity.getPhone());
		assertEquals(departmentEntity.getName(), entity.getDepartment().getName());
		assertEquals(departmentEntity.getLocation(), entity.getDepartment().getLocation());
		assertEquals(idCardEntity.getIssue_date(), entity.getIdcard().getIssue_date());
		assertTrue(entity.getProjects().isEmpty());
	}

	@Test
	public void testIDCardMapping() {
		IDCardDTO dto = IDCardDTOMapper.toDTO(idCardEntity);
		assertNotNull(dto);
		assertEquals("2024-01-01", dto.getIssueDate());
		assertEquals("2025-01-01", dto.getExpiryDate());
		assertEquals(employeeDTO.getName(), dto.getEmployee().getName());

		IDCard entity = IDCardDTOMapper.toEntity(dto);
		assertNotNull(entity);
		assertEquals("2024-01-01", entity.getIssue_date());
		assertEquals("2025-01-01", entity.getExpiry_date());
		assertEquals(employeeEntity.getName(), entity.getEmployee().getName());
	}

	@Test
	public void testProjectMapping() {
		ProjectDTO dto = ProjectDTOMapper.toDTO(projectEntity);
		assertNotNull(dto);
		assertEquals("Project A", dto.getName());
		assertEquals("2024-06-12", dto.getStartDate());
		assertEquals("2024-12-31", dto.getEndDate());
		assertTrue(dto.getEmployees().isEmpty());
		assertTrue(dto.getTasks().isEmpty());

		Project entity = ProjectDTOMapper.toEntity(dto);
		assertNotNull(entity);
		assertEquals("Project A", entity.getName());
		assertEquals("2024-06-12", entity.getStart_date());
		assertEquals("2024-12-31", entity.getEnd_date());
		assertTrue(entity.getEmployees().isEmpty());
		assertTrue(entity.getTasks().isEmpty());
	}

	@Test
	public void testTaskMapping() {
		TaskDTO dto = TaskDTOMapper.toDTO(taskEntity);
		assertNotNull(dto);
		assertEquals("Develop REST API", dto.getDescription());
		assertEquals(TaskStatus.InProgress, dto.getStatus());
		assertEquals(projectDTO.getName(), dto.getProject().getName());

		Task entity = TaskDTOMapper.toEntity(dto);
		assertNotNull(entity);
		assertEquals("Develop REST API", entity.getDescription());
		assertEquals(TaskStatus.InProgress, entity.getStatus());
		assertEquals(projectEntity.getName(), entity.getProject().getName());
	}
}

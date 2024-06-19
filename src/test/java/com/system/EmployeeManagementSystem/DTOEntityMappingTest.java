package com.system.EmployeeManagementSystem;

import static org.junit.jupiter.api.Assertions.*;

import com.system.EmployeeManagementSystem.DTOs.*;
import com.system.EmployeeManagementSystem.mapper.DepartmentDTOMapper;
import com.system.EmployeeManagementSystem.mapper.EmployeeDTOMapper;
import com.system.EmployeeManagementSystem.mapper.IDCardDTOMapper;
import com.system.EmployeeManagementSystem.mapper.ProjectDTOMapper;
import com.system.EmployeeManagementSystem.mapper.TaskDTOMapper;
import com.system.EmployeeManagementSystem.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Set;

class DTOEntityMappingTest {

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
		departmentDTO = DepartmentDTO.builder()
				.name("Human Resources")
				.location("California")
				.employees(Set.of())
				.build();

		idCardDTO = IDCardDTO.builder()
				.issueDate("2024-01-01")
				.expiryDate("2025-01-01")
				.build();

		projectDTO = ProjectDTO.builder()
				.name("Project A")
				.startDate("2024-06-12")
				.endDate("2024-12-31")
				.employees(Set.of())
				.tasks(Set.of())
				.build();

		taskDTO = TaskDTO.builder()
				.description("Develop REST API")
				.status(TaskStatus.InProgress)
				.build();

		employeeDTO = EmployeeDTO.builder()
				.name("Bobby")
				.email("bobby@gmail.com")
				.phone("+371 20 321 425")
				.department(departmentDTO)
				.idCard(idCardDTO)
				.projects(Set.of())
				.build();

		departmentEntity = Department.builder()
				.name("Human Resources")
				.location("California")
				.employees(Set.of())
				.build();

		idCardEntity = IDCard.builder()
				.issue_date("2024-01-01")
				.expiry_date("2025-01-01")
				.build();

		projectEntity = Project.builder()
				.name("Project A")
				.start_date("2024-06-12")
				.end_date("2024-12-31")
				.employees(Set.of())
				.tasks(Set.of())
				.build();

		taskEntity = Task.builder()
				.description("Develop REST API")
				.status(TaskStatus.InProgress)
				.project(projectEntity)
				.build();

		employeeEntity = Employee.builder()
				.name("Bobby")
				.email("bobby@gmail.com")
				.phone("+371 20 321 425")
				.department(departmentEntity)
				.idcard(idCardEntity)
				.projects(Set.of())
				.build();

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

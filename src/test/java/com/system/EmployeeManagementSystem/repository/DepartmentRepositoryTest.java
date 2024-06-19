package com.system.EmployeeManagementSystem.repository;

import com.system.EmployeeManagementSystem.models.Department;
import com.system.EmployeeManagementSystem.repositories.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department departmentOne, departmentTwo;

    @BeforeEach
    public void setUp(){
        departmentOne = Department.builder()
                .name("Cool department")
                .location("Cool place")
                .employees(Set.of())
                .build();

        departmentTwo = Department.builder()
                .name("Cool department2")
                .location("Cool place2")
                .employees(Set.of())
                .build();
    }

    @Test
    public void DepartmentRepositorySaveTest(){
        Department savedDepartment = departmentRepository.save(departmentOne);

        Assertions.assertNotNull(savedDepartment);
        Assertions.assertTrue(savedDepartment.getId() > 0);
    }

    @Test
    public void DepartmentRepositoryGetAllTest(){

        departmentRepository.save(departmentOne);
        departmentRepository.save(departmentTwo);

        List<Department> departmentsList = departmentRepository.findAll();

        Assertions.assertNotNull(departmentsList);
        Assertions.assertEquals(departmentOne, departmentsList.getFirst());
        Assertions.assertEquals(departmentTwo, departmentsList.getLast());
    }

    @Test
    public void DepartmentRepositoryGetByIDTest(){

        departmentRepository.save(departmentOne);
        Department savedDepartment = departmentRepository.save(departmentTwo);

        Department departmentById = departmentRepository.findById(savedDepartment.getId()).get();

        Assertions.assertNotNull(departmentById);
        Assertions.assertEquals(departmentTwo, departmentById);
    }

    @Test
    public void DepartmentRepositoryDeleteTest(){
        departmentRepository.save(departmentOne);

        departmentRepository.deleteById(departmentOne.getId());

        Assertions.assertFalse(departmentRepository.findById(departmentOne.getId()).isPresent());
    }
}

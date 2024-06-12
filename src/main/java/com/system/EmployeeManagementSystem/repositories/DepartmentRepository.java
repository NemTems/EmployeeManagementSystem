package com.system.EmployeeManagementSystem.repositories;

import com.system.EmployeeManagementSystem.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}

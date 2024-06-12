package com.system.EmployeeManagementSystem.repositories;

import com.system.EmployeeManagementSystem.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}

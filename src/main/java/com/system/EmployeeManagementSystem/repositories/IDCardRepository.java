package com.system.EmployeeManagementSystem.repositories;

import com.system.EmployeeManagementSystem.models.IDCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDCardRepository extends JpaRepository<IDCard, Integer> {
}

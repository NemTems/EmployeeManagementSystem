package com.system.EmployeeManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @JoinColumn(name = "department_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Department department;

    @JoinColumn(name = "idcard_id", referencedColumnName = "id")
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private IDCard idcard;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinTable(
            name = "EmployeeProject",
            joinColumns = { @JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id")}
    )
    private Set<Project> projects = new HashSet<>();
}

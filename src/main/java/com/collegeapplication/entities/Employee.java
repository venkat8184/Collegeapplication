package com.collegeapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    @Column(name="email",unique = true)
    private String email;
    @Column(name="mobile",unique = true)
    private String mobile;
    private int salary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_Id",nullable = false)
    private Department department;
}

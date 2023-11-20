package com.projects.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.springboot.models.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

}

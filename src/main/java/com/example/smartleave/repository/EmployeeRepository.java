package com.example.smartleave.repository;

import com.example.smartleave.entity.User;
import com.example.smartleave.entity.Manager;
import com.example.smartleave.entity.Employee;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUser(User user);
    List<Employee> findByManager(Manager manager);
}
package com.example.smartleave.repository;

import com.example.smartleave.entity.Manager;
import com.example.smartleave.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findByUser(User user);
    List<Manager> findByDepartment(String department);
}
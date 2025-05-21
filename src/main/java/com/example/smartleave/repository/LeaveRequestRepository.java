package com.example.smartleave.repository;

import com.example.smartleave.entity.Employee;
import com.example.smartleave.entity.LeaveRequest;
import com.example.smartleave.entity.LeaveStatus;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployee(Employee emp);
    List<LeaveRequest> findByStatus(LeaveStatus status);
    List<LeaveRequest> findByType(String leaveType);
    List<LeaveRequest> findByEmployeeAndStatus(Employee emp, LeaveStatus status);
    List<LeaveRequest> findByStartDateBetween(LocalDate from, LocalDate to);
    // 新增：分頁顯示某位員工的請假單
    Page<LeaveRequest> findByEmployee(Employee emp, Pageable pageable);
    // 新增：分頁顯示特定狀態的請假單
    Page<LeaveRequest> findByStatus(LeaveStatus status, Pageable pageable);
}

package com.example.smartleave.service;

import com.example.smartleave.entity.*;
import com.example.smartleave.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LeaveService {
    private final LeaveRequestRepository leaveRepo;
    private final EmployeeRepository empRepo;
    private final ManagerRepository mgrRepo;

    public LeaveService(LeaveRequestRepository leaveRepo,
                        EmployeeRepository empRepo,
                        ManagerRepository mgrRepo) {
        this.leaveRepo = leaveRepo;
        this.empRepo = empRepo;
        this.mgrRepo = mgrRepo;
    }

    /** 申請請假 */
    @Transactional
    public LeaveRequest applyLeave(Long empId, LocalDate start, LocalDate end, String leaveType, String reason) {
        Employee emp = empRepo.findById(empId)
            .orElseThrow(() -> new IllegalArgumentException("找不到員工：" + empId));
        LeaveRequest lr = new LeaveRequest();
        lr.setEmployee(emp);
        lr.setStartDate(start);
        lr.setEndDate(end);
        lr.setType(leaveType);
        lr.setReason(reason);
        lr.setCreatedTime(LocalDateTime.now());
        lr.setStatus(LeaveStatus.PENDING);
        return leaveRepo.save(lr);
    }

    /** 審核請假 */
    @Transactional
    public LeaveRequest reviewLeave(Long leaveId,
                                    Long mgrId,
                                    boolean approve) {
        LeaveRequest lr = leaveRepo.findById(leaveId)
            .orElseThrow(() -> new IllegalArgumentException("找不到請假單：" + leaveId));
        Manager mgr = mgrRepo.findById(mgrId)
            .orElseThrow(() -> new IllegalArgumentException("找不到主管：" + mgrId));
        // 驗證該主管是否負責此員工
        if (!mgr.equals(lr.getEmployee().getManager())) {
            throw new IllegalStateException("您不是此請假單的負責主管");
        }
        lr.setStatus(approve ? LeaveStatus.APPROVED : LeaveStatus.REJECTED);
        return leaveRepo.save(lr);
    }

    /** 查詢某員工所有請假單 */
    public List<LeaveRequest> listByEmployee(Long empId) {
        Employee emp = empRepo.findById(empId)
            .orElseThrow(() -> new IllegalArgumentException("找不到員工：" + empId));
        return leaveRepo.findByEmployee(emp);
    }

    /** 查詢某狀態的請假單 */
    public List<LeaveRequest> listByStatus(LeaveStatus status) {
        return leaveRepo.findByStatus(status);
    }
}
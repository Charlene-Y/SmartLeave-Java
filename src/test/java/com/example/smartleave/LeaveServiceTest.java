package com.example.smartleave.service;

import com.example.smartleave.entity.*;
import com.example.smartleave.repository.*;
import com.example.smartleave.service.LeaveService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class LeaveServiceTest {

    @Mock
    private LeaveRequestRepository leaveRepo;
    @Mock
    private EmployeeRepository empRepo;
    @Mock
    private ManagerRepository mgrRepo;

    @InjectMocks
    private LeaveService leaveService;

    private Employee  emp;
    private Manager   mgr;
    private LeaveRequest lr;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化一個 Employee、Manager 與 LeaveRequest
        emp = new Employee();
        emp.setEmpId(1L);

        mgr = new Manager();
        mgr.setMgrId(2L);

        emp.setManager(mgr);

        lr = new LeaveRequest();
        lr.setLeaveId(10L);
        lr.setEmployee(emp);
        lr.setStartDate(LocalDate.now());
        lr.setEndDate(LocalDate.now().plusDays(1));
        lr.setCreatedTime(LocalDateTime.now());
        lr.setStatus(LeaveStatus.PENDING);
    }

    @Test
    void testApplyLeaveSuccess() {
        // 模擬 empRepo.findById 回傳 emp
        when(empRepo.findById(1L)).thenReturn(Optional.of(emp));
        // 模擬 save 回傳同一個 lr 物件
        when(leaveRepo.save(any(LeaveRequest.class))).thenAnswer(i -> {
            LeaveRequest arg = i.getArgument(0);
            arg.setLeaveId(100L);
            return arg;
        });

        LeaveRequest result = leaveService.applyLeave(
            1L,
            LocalDate.of(2025,5,1),
            LocalDate.of(2025,5,3),
            "Annual Leave",
            "測試理由"
        );

        assertNotNull(result);
        assertEquals(100L, result.getLeaveId());
        assertEquals(LeaveStatus.PENDING, result.getStatus());
        verify(empRepo, times(1)).findById(1L);
        verify(leaveRepo, times(1)).save(any());
    }

    @Test
    void testReviewLeaveNotManager() {
        // 模擬 leaveRepo
        when(leaveRepo.findById(10L)).thenReturn(Optional.of(lr));

        // 正確：先建立一個 Manager 物件，再呼叫 setter
        Manager wrongMgr = new Manager();
        wrongMgr.setMgrId(999L);

        when(mgrRepo.findById(999L))
            .thenReturn(Optional.of(wrongMgr));

        // 由於 999 不是 lr.getEmployee().getManager()，應拋 IllegalStateException
        assertThrows(IllegalStateException.class, () ->
            leaveService.reviewLeave(10L, 999L, true)
        );
    }

    @Test
    void testReviewLeaveSuccess() {
        // 模擬 leaveRepo 與 mgrRepo
        when(leaveRepo.findById(10L)).thenReturn(Optional.of(lr));
        when(mgrRepo.findById(2L)).thenReturn(Optional.of(mgr));
        when(leaveRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        LeaveRequest updated = leaveService.reviewLeave(10L, 2L, true);

        assertEquals(LeaveStatus.APPROVED, updated.getStatus());
        verify(leaveRepo, times(1)).save(lr);
    }
}
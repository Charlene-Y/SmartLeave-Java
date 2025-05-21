package com.example.smartleave.controller;

import com.example.smartleave.entity.LeaveRequest;
import com.example.smartleave.entity.LeaveStatus;
import com.example.smartleave.service.LeaveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    /** 1. 申請請假 */
    @PostMapping
    public ResponseEntity<LeaveRequest> applyLeave(@RequestBody ApplyLeaveDto dto) {
        LeaveRequest saved = leaveService.applyLeave(
            dto.getEmpId(),
            LocalDate.parse(dto.getStartDate()),
            LocalDate.parse(dto.getEndDate()),
            dto.getLeaveType(),
            dto.getReason()
        );
        return ResponseEntity.ok(saved);
    }

    /** 2. 審核請假（approve=true 批准，false 駁回） */
    @PutMapping("/{id}/review")
    public ResponseEntity<LeaveRequest> reviewLeave(
            @PathVariable Long id,
            @RequestParam Long mgrId,
            @RequestParam boolean approve
    ) {
        LeaveRequest updated = leaveService.reviewLeave(id, mgrId, approve);
        return ResponseEntity.ok(updated);
    }

    /** 3. 查詢某員工的所有請假單 */
    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<LeaveRequest>> listByEmployee(@PathVariable Long empId) {
        return ResponseEntity.ok(leaveService.listByEmployee(empId));
    }

    /** 4. 查詢特定狀態的請假單 */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LeaveRequest>> listByStatus(@PathVariable LeaveStatus status) {
        return ResponseEntity.ok(leaveService.listByStatus(status));
    }

    /** DTO：申請請假時傳的 JSON */
    public static class ApplyLeaveDto {
        private Long empId;
        private String startDate;   // yyyy-MM-dd
        private String endDate;     // yyyy-MM-dd
        private String leaveType;
        private String reason;
        // getters & setters
        public Long getEmpId() { return empId; }
        public void setEmpId(Long empId) { this.empId = empId; }
        public String getStartDate() { return startDate; }
        public void setStartDate(String startDate) { this.startDate = startDate; }
        public String getEndDate() { return endDate; }
        public void setEndDate(String endDate) { this.endDate = endDate; }
        public String getLeaveType() { return leaveType; }
        public void setLeaveType(String leaveType) { this.leaveType = leaveType; }
        public String getReason() { return reason; }
        public void setReason(String reason) { this.reason = reason; }
    }
}

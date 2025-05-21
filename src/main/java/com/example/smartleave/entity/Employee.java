package com.example.smartleave.entity;

import com.example.smartleave.entity.User;
import com.example.smartleave.entity.Manager;
import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;        // PK: emp_id

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // FK → User

    @ManyToOne
    @JoinColumn(name = "mgr_id")
    private Manager manager;  // FK → Manager

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "total_leave_days")
    private Integer totalLeaveDays;

    public Employee() {
    }

    public Long getEmpId() {
        return empId;
    }
    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Manager getManager() {
        return manager;
    }
    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getTotalLeaveDays() {
        return totalLeaveDays;
    }
    public void setTotalLeaveDays(Integer totalLeaveDays) {
        this.totalLeaveDays = totalLeaveDays;
    }
}

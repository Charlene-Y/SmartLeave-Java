package com.example.smartleave.entity;

import com.example.smartleave.entity.User;
import jakarta.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mgr_id")
    private Long mgrId;        // PK: mgr_id

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;   // FK → User

    @Column(name = "department")
    private String department;

    @Column(name = "position")
    private String position;

    public Manager() {
    }

    public Long getMgrId() {
        return mgrId;
    }
    public void setMgrId(Long mgrId) {
        this.mgrId = mgrId;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
}


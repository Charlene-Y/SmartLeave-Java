package com.example.smartleave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.smartleave.entity.*;
import com.example.smartleave.repository.*;
import java.time.LocalDate;
import java.time.LocalDateTime;*/

@SpringBootApplication
public class SmartleaveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartleaveApplication.class, args);
	}

	/*@Bean
    CommandLineRunner initData(
            UserRepository userRepo,
            EmployeeRepository empRepo,
            ManagerRepository mgrRepo,
            LeaveRequestRepository leaveRepo
    ) { 
        return args -> {
            // 1. 建立兩個使用者帳號
            User u1 = new User();
            u1.setUsername("alice");
            u1.setPassword("pass123");
            u1.setRole("EMPLOYEE");
            userRepo.save(u1);

            User u2 = new User();
            u2.setUsername("bob");
            u2.setPassword("pass456");
            u2.setRole("MANAGER");
            userRepo.save(u2);

            // 2. 對應 Employee 與 Manager
            Employee e1 = new Employee();
            e1.setUser(u1);
            e1.setJobTitle("Developer");
            e1.setTotalLeaveDays(14);
            empRepo.save(e1);

            Manager m1 = new Manager();
            m1.setUser(u2);
            m1.setDepartment("IT");
            m1.setPosition("Team Lead");
            mgrRepo.save(m1);

            // 3. 設定 Employee 的主管
            e1.setManager(m1);
            empRepo.save(e1);

            // 4. 新增一筆請假單
            LeaveRequest lr = new LeaveRequest();
            lr.setEmployee(e1);
            lr.setStartDate(LocalDate.now().plusDays(1));
            lr.setEndDate(LocalDate.now().plusDays(3));
            lr.setType("Annual Leave");
            lr.setReason("Family event");
            lr.setCreatedTime(LocalDateTime.now());
            lr.setStatus(LeaveStatus.PENDING);
            leaveRepo.save(lr);

            System.out.println("==== 初始化測試資料完成 ====");
        };
    } */

}

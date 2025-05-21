package com.example.smartleave.controller;

import com.example.smartleave.entity.Employee;
import com.example.smartleave.entity.Manager;
import com.example.smartleave.entity.User;
import com.example.smartleave.repository.EmployeeRepository;
import com.example.smartleave.repository.ManagerRepository;
import com.example.smartleave.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final EmployeeRepository empRepo;
    private final ManagerRepository mgrRepo;

    public AuthController(UserRepository userRepo, EmployeeRepository empRepo, ManagerRepository mgrRepo) {
        this.userRepo = userRepo;
        this.empRepo = empRepo;
        this.mgrRepo = mgrRepo;
    }

    public static class LoginDto {
        public String username;
        public String password;
        // getters/setters or public fields
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDto dto) {
        // 1. 查帳號，不存在就 401
        User u = userRepo.findByUsername(dto.username)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.UNAUTHORIZED, "帳號或密碼錯誤")
            );

        // 2. 驗證密碼，不合就 401
        if (!u.getPassword().equals(dto.password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "帳號或密碼錯誤");
        }

        // 3. 組結果 Map
        Map<String, Object> result = new HashMap<>();
        result.put("username", u.getUsername());
        result.put("role", u.getRole());

        if ("EMPLOYEE".equals(u.getRole())) {
            Employee e = empRepo.findByUser(u)
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "員工資料不存在")
                );
            result.put("empId", e.getEmpId());
        } else if ("MANAGER".equals(u.getRole())) {
            Manager m = mgrRepo.findByUser(u)
                .orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "主管資料不存在")
                );
            result.put("mgrId", m.getMgrId());
        }

        return ResponseEntity.ok(result);
    }
}

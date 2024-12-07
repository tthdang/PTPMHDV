package com.duc.manager.controller;

import com.duc.manager.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:9090")
@RestController
@RequestMapping("/logins")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping("/weekly-login-stats")
    public Map<String, Integer> getWeeklyLoginStats() {
        return loginService.getLoginStatsForWeek();
    }

    @GetMapping("/login-statistics")
    public ResponseEntity<Map<String, Long>> getLoginStatisticsForMonth(@RequestParam String month) {
        // month là chuỗi theo định dạng "YYYY-MM" (ví dụ: "2024-12")
        Map<String, Long> loginStats = loginService.getLoginStatisticsForMonth(month);
        return ResponseEntity.ok(loginStats);
    }
}

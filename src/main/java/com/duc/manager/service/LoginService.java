package com.duc.manager.service;

import com.duc.manager.entity.UserLogin;
import com.duc.manager.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public Map<String, Integer> getLoginStatsForWeek() {
        Map<String, Integer> loginStats = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();

        for (int i = 0; i < 7; i++) {
            LocalDate date = today.minusDays(i);
            int loginCount = loginRepository.countByLoginDate(date);
            loginStats.put(date.toString(), loginCount);
        }

        return loginStats;
    }

    public Map<String, Long> getLoginStatisticsForMonth(String month) {
        LocalDate startDate = LocalDate.parse(month + "-01");  // Convert "YYYY-MM-01"
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());  // Get the last day of the month

        List<UserLogin> histories = loginRepository.findByLoginTimeBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX));

        Map<String, Long> loginStats = new HashMap<>();

        for (UserLogin history : histories) {
            String day = history.getLoginTime().toLocalDate().toString();  // Extract date (YYYY-MM-DD)
            loginStats.put(day, loginStats.getOrDefault(day, 0L) + 1);
        }

        return loginStats;
    }
}

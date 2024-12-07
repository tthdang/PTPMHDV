package com.duc.manager.repository;

import com.duc.manager.entity.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<UserLogin, Long> {

    @Query("SELECT COUNT(l) FROM UserLogin l WHERE DATE(l.loginTime) = :date")
    int countByLoginDate(@Param("date") LocalDate date);

    List<UserLogin> findByLoginTimeBetween(LocalDateTime start, LocalDateTime end);
}

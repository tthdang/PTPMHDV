package com.duc.manager.repository;

import com.duc.manager.entity.Customers;
import com.duc.manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customers,Integer> {
    Optional<Customers> findByEmail(String email);

    @Query(value = "SELECT " +
            "customers.id as customer_id, " +
            "customers.name as customer_name, " +
            "customers.address as customer_address, " +
            "customers.email as customer_email, " +
            "customers.phone as customer_phone, " +
            "user.id as user_id, " +
            "user.username as user_username, " +
            "user.password as user_password " +
            "FROM Customers customers " +
            "JOIN User user ON customers.user_id = user.id",
            nativeQuery = true)
    List<Map<String, Object>> findCustomerWithUser();

    @Query(value = "SELECT " +
            "customers.customer_id as customer_id, " +
            "customers.name as customer_name, " +
            "customers.address as customer_address, " +
            "customers.email as customer_email, " +
            "customers.phone as customer_phone, " +
            "user.id as user_id, " +
            "user.username as user_username, " +
            "user.password as user_password " +
            "FROM Customers customers " +
            "JOIN User user ON customers.user_id = user.id",
            nativeQuery = true)
    List<Map<String, Object>> findCustomerWithUserById(@Param("customerId") int customerId);



}

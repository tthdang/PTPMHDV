package com.duc.manager.service;

import com.duc.manager.entity.Customers;
import com.duc.manager.entity.User;
import com.duc.manager.repository.CustomerRepository;
import com.duc.manager.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository CustomerRepository;

    @Autowired
    private UserRepository userRepository;

//    public Customers createCustomer(CustomerCreationRequest request){
//        Customers customer= new Customers();
//
//        customer.setName(request.getName());
//        customer.setEmail(request.getEmail());
//        customer.setPhone(request.getPhone());
//        customer.setAddress(request.getAddress());
//
//        return CustomerRepository.save(customer);
//    }

    public List<Customers> getCustomers(){
        return CustomerRepository.findAll();
    }

    public Customers getCustomer(int Id){
        return CustomerRepository.findById(Id).orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    @Transactional
    public Customers createCustomerWithUser(String username, String password, String name, String email, String phone, String address) {
        // Tạo User
        User user = new User();

        if (userRepository.existsByUsername(username))
            throw new RuntimeException("User existed.");

        user.setUsername(username);
        user.setPassword(password);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(password));

        // Tạo Customer và liên kết với User
        Customers customer = new Customers();
        customer.setName(name);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setUser(user);

        // Lưu vào database
        return CustomerRepository.save(customer);
    }

    public List<Map<String, Object>> getCustomersWithUsers() {
        return CustomerRepository.findCustomerWithUser();
    }

    public List<Map<String, Object>> getCustomerWithUserById(int customerId) {
        return CustomerRepository.findCustomerWithUserById(customerId);


    }
}

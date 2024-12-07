package com.duc.manager.service;

import com.duc.manager.dto.request.UserCreationRequest;
import com.duc.manager.entity.User;
import com.duc.manager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

//        if (userRepository.existsByUsername(request.getUsername()))
//            throw new RuntimeException("User existed.");

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getUser(){
        return userRepository.findAll();
    }

    public User getUser(String user_id){
        return  userRepository.findById(user_id).orElseThrow(() -> new RuntimeException("User not found!"));
    }
}

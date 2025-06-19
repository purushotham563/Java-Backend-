package com.validation.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User1 CreateUser(User1 user){
        return userRepository.save(user);
    }
}

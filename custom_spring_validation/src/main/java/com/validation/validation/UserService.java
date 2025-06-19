package com.validation.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User CreateUser(User user){
        return userRepository.save(user);
    }
}

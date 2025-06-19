package com.validation.validation;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?>createUser(@Valid @RequestBody User1 user1,BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        User1 savedUser=userService.CreateUser(user1);

        return ResponseEntity.ok(savedUser);

    }
    @PostMapping("/users1")
    public ResponseEntity<User1>createUser1(@Valid @RequestBody User1 user1){
        User1 savedUser=userService.CreateUser(user1);
        return ResponseEntity.ok(savedUser);
    }

}

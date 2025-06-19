package com.validation.validation;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        return ResponseEntity.ok(user);
    }
}

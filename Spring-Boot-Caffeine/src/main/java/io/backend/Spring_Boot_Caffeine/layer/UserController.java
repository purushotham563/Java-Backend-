package io.backend.Spring_Boot_Caffeine.layer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).
                orElse(ResponseEntity.notFound().build());

    }
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUserName(@PathVariable String username){
        Optional<User>user=userService.getUserByUsername(username);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public List<User>getAllUser(){
        return userService.getAllUser();
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/cache/clear")
    public ResponseEntity<String> clearCache(){
        userService.clearAllUsers();
        return ResponseEntity.ok("Cache cleared successfully!");
    }
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String name) {
        return userService.searchUserByName(name);
    }
}

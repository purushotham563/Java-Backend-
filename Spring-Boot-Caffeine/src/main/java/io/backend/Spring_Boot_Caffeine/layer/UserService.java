package io.backend.Spring_Boot_Caffeine.layer;

import com.fasterxml.jackson.annotation.OptBoolean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(key = "#id")
    public Optional<User>getUserById(Long id){
        System.out.println("fetching user from database");
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return userRepository.findById(id);
    }
    @Cacheable(key="#username",value = "user")
    public Optional<User>getUserByUsername(String username){
        System.out.println("Fetching user by username from database"+username);
        return userRepository.findByUsername(username);
    }
    @Cacheable(key="#email",value = "user",condition = "#email.length()>5")
    public Optional<User>getUserByEmail(String email){
        System.out.println("Fetching user by username from database"+email);
        return userRepository.findByEmail(email);
    }
    @Cacheable(key = "'all_users'")
    public List<User>getAllUser(){
        System.out.println("Fetching all user from database");
        return userRepository.findAll();
    }
    @CachePut(key="#user.id")
    public User saveUser(User user){
        System.out.println("Saving user to database"+user.getUsername());
        return userRepository.save(user);
    }
    @CacheEvict(key="#id")
    public void deleteUser(Long id){
        System.out.println("Deleting user from database");
        userRepository.deleteById(id);
    }
    @CacheEvict(allEntries = true)
    public void clearAllUsers(){
        System.out.println("Clearing all user cache");
    }
    @Caching(cacheable = @Cacheable(key="name"),evict = @CacheEvict(key="'all_users'"))
    public List<User>searchUserByName(String name){
        System.out.println("Searching user by name"+name);
        return userRepository.findByFirstNameContaining(name);
    }





}

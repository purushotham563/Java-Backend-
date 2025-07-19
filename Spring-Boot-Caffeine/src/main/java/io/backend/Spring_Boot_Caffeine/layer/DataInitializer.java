package io.backend.Spring_Boot_Caffeine.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void run(String... args) throws Exception {
        // Add test data
        if (userRepository.count() == 0) {
            userRepository.save(new User("john_doe", "john@example.com", "John", "Doe"));
            userRepository.save(new User("jane_smith", "jane@example.com", "Jane", "Smith"));
            userRepository.save(new User("bob_wilson", "bob@example.com", "Bob", "Wilson"));
            userRepository.save(new User("alice_brown", "alice@example.com", "Alice", "Brown"));

            System.out.println("✅ Test data initialized!");
        }
    }
}

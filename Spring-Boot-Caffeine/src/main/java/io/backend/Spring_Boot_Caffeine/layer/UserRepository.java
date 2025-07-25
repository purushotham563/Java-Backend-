package io.backend.Spring_Boot_Caffeine.layer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findByUsername(String username);
    Optional<User>findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.firstName LIKE %:name%")
    List<User> findByFirstNameContaining(@Param("name") String name);
}

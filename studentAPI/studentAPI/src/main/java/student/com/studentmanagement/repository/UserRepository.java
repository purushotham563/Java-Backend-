package student.com.studentmanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import student.com.studentmanagement.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

package in.coder.abhijit.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import in.coder.abhijit.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

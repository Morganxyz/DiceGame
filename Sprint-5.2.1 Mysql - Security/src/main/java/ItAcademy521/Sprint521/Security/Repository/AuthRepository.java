package ItAcademy521.Sprint521.Security.Repository;

import ItAcademy521.Sprint521.Security.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}

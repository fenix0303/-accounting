package ua.sych.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.sych.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

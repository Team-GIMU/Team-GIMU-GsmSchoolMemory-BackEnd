package com.gsm.domain.user.repository;



import com.gsm.domain.user.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    User findUserByEmail(String email);
}

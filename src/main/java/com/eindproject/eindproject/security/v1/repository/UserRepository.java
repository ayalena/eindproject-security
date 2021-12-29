package com.eindproject.eindproject.security.v1.repository;

import com.eindproject.eindproject.security.v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
//    User findByUsername(String username);
}

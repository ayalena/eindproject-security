package com.eindproject.eindproject.security.v1.repository;

import com.eindproject.eindproject.security.v1.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}

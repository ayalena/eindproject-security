package com.eindproject.eindproject.security.v1.repository;

import com.eindproject.eindproject.security.v1.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository <UserProfile, Long> {
}

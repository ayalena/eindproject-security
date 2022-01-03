package com.eindproject.eindproject.security.v1.repository;

import com.eindproject.eindproject.security.v1.model.Authority;
import com.eindproject.eindproject.security.v1.model.EAuthority;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("authrepo")
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    Optional<Object> findByName(EAuthority roleUser);
}

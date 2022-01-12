package com.eindproject.eindproject.security.v1.repository;

import com.eindproject.eindproject.security.v1.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}

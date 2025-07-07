package com.jio.onboarding.repository;

import com.jio.onboarding.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

package com.qursed.springbootbutbetter.repository;

import com.qursed.springbootbutbetter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}

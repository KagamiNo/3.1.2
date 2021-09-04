package com.qursed.springbootbutbetter.repository;


import com.qursed.springbootbutbetter.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String name);
}

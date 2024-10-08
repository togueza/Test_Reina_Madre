package com.reina.madre.repository;

import com.reina.madre.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByusername(String username);
}

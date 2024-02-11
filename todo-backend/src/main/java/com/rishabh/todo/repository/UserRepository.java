package com.rishabh.todo.repository;

import com.rishabh.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String name);

    Boolean existsByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username,String email);

    Boolean existsByUsername(String username);
}

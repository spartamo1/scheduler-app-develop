package io.github.prjkmo112.schedulerappdevelop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.prjkmo112.schedulerappdevelop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
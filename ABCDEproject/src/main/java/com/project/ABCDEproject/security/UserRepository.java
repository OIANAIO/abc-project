package com.project.ABCDEproject.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.ABCDEproject.vo.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByName(String name);
}
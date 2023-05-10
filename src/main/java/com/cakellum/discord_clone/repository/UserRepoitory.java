package com.cakellum.discord_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakellum.discord_clone.model.User;

public interface UserRepoitory extends JpaRepository<User, Long> {
}

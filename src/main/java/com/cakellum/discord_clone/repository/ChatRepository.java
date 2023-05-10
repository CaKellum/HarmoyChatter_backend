package com.cakellum.discord_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakellum.discord_clone.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}

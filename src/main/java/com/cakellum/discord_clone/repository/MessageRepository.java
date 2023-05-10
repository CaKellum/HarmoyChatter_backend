package com.cakellum.discord_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cakellum.discord_clone.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}

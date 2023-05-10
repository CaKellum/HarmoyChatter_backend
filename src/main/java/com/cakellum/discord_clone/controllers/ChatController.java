package com.cakellum.discord_clone.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.cakellum.discord_clone.model.Chat;
import com.cakellum.discord_clone.model.Message;
import com.cakellum.discord_clone.model.User;
import com.cakellum.discord_clone.repository.ChatRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@AllArgsConstructor
public class ChatController implements ModelController<Chat> {

    private final ChatRepository repository;

    @Override
    @GetMapping("/chats/{id}")
    public Chat findByID(@PathVariable long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @GetMapping("/chats")
    public List<Chat> findAll() {
        return repository.findAll();
    }

    @GetMapping("/user/chats/{userId}")
    public List<Chat> getChatsForUser(@PathVariable long userId) {
        return repository.findAll().stream()
                .filter(chat -> (userId == chat.getOwner().getUserId() || chat.isUserMember(userId)))
                .collect(Collectors.toList());
    }

    @GetMapping("/user/chat/{chatId}")
    public List<Message> getChatLog(@PathVariable long chatId) {
        return findByID(chatId).getChatLog();
    }

    @GetMapping("user/chat/{chatId}/{userId}")
    public void kickMemberFromChat(@PathVariable long chatId, @PathVariable long userId) {
        findByID(chatId).kickMember(userId);
    }

    @Override
    @PostMapping("/chat")
    public void add(@RequestBody Chat newObject) {
        repository.save(newObject);
    }

    @PostMapping("/chat/{chatId}/addUser")
    public void addMember(@RequestBody User user, @PathVariable long chatId) {
        findByID(chatId).addMember(user);
    }

    @Override
    @DeleteMapping("/chat/{id}")
    public void deleteByID(@PathVariable long id) {
        repository.deleteById(id);
    }

    @Override
    @DeleteMapping("/chat")
    public void deleteObject(@RequestBody Chat unwanted) {
        repository.delete(unwanted);
    }

    @Override
    @PutMapping("/chat")
    public void updateObject(@RequestBody Chat updated) {
        repository.findById(updated.getChatId()).map(chat -> {
            chat.setMembersList(updated.getMembersList());
            chat.setChatLog(updated.getChatLog());
            chat.setTitle(updated.getTitle());
            chat.setOwner(updated.getOwner());
            return repository.save(chat);
        }).orElseGet(() -> {
            return repository.save(updated);
        });
    }
}

package com.cakellum.discord_clone.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.cakellum.discord_clone.model.Message;
import com.cakellum.discord_clone.repository.MessageRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class MessageController implements ModelController<Message> {

    private final MessageRepository repository;
    private final static String MESSAGE_PATH_PREFIX = "/message";
    private final static String DELETE_PATH = "/delete";
    private final static String ID_KEY = "/{id}";
    private final static String NEW_MESSAGE_PATH = MESSAGE_PATH_PREFIX + "/new";
    private final static String GET_MSG_BY_ID_PATH = MESSAGE_PATH_PREFIX + ID_KEY;
    private final static String DELETE_MSG_BY_ID_PATH = GET_MSG_BY_ID_PATH + DELETE_PATH;
    private final static String DELETE_MSG_PATH = MESSAGE_PATH_PREFIX + DELETE_PATH;
    private final static String UPDATE_MSG_PATH = MESSAGE_PATH_PREFIX + "/edit";

    @Override
    @GetMapping(GET_MSG_BY_ID_PATH)
    public Message findByID(@PathVariable long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    @GetMapping(MESSAGE_PATH_PREFIX)
    public List<Message> findAll() {
        return repository.findAll();
    }

    @Override
    @PostMapping(NEW_MESSAGE_PATH)
    public void add(@RequestBody Message newObject) {
        repository.save(newObject);
    }

    @Override
    @DeleteMapping(DELETE_MSG_BY_ID_PATH)
    public void deleteByID(long id) {
        repository.deleteById(id);
    }

    @Override
    @DeleteMapping(DELETE_MSG_PATH)
    public void deleteObject(@RequestBody Message unwanted) {
        repository.delete(unwanted);
    }

    @Override
    @PutMapping(UPDATE_MSG_PATH)
    public void updateObject(Message updated) {
        repository.findById(updated.getMessageId()).map(msg -> {
            msg.editMessage(updated.getContent());
            return repository.save(msg);
        }).orElseGet(() -> {
            return repository.save(updated);
        });
    }

}

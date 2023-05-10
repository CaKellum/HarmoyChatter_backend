package com.cakellum.discord_clone.controllers;

import java.util.List;

public interface ModelController<M> {
    public M findByID(long id);

    public List<M> findAll();

    public void add(M newObject);

    public void deleteByID(long id);

    public void deleteObject(M unwanted);

    public void updateObject(M updated);
}

package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Repeating;

import java.util.List;

public interface RepeatingService {
    List<Repeating> getAll();
    Repeating save(Repeating entity, long userId);
    void remove(long id);
    Repeating getById(long id);
}

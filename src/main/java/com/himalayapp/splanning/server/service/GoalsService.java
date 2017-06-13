package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Goal;

import java.util.List;

public interface GoalsService {
    List<Goal> getAll();
    Goal save(Goal entity, long userId);
    void remove(long id, long userId);
    Goal getById(long id);
}

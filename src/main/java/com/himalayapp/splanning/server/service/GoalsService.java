package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Goals;

import java.util.List;

public interface GoalsService {
    List<Goals> getAll();
    Goals save(Goals entity, long userId);
    void remove(long id);
    Goals getById(long id);
}

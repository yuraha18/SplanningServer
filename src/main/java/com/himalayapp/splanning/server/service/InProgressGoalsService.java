package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.InProgressGoals;

import java.util.List;

public interface InProgressGoalsService {
    List<InProgressGoals> getAll();
    InProgressGoals save(InProgressGoals entity, long userId);
    void remove(long id);
    InProgressGoals getById(long id);
}

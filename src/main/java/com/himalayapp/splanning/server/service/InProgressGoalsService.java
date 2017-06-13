package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.InProgressGoal;

import java.util.List;

public interface InProgressGoalsService {
    List<InProgressGoal> getAll();
    InProgressGoal save(InProgressGoal entity, long userId);
    void remove(long id, long userId);
    InProgressGoal getById(long id);
}

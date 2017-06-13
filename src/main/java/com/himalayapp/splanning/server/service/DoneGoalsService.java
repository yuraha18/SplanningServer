package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.DoneGoal;

import java.util.List;

public interface DoneGoalsService {
    List<DoneGoal> getAll();
    DoneGoal save(DoneGoal entity, long userId);
    DoneGoal getById(long id);
}

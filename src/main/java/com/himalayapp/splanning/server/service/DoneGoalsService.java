package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.DoneGoals;

import java.util.List;

public interface DoneGoalsService {
    List<DoneGoals> getAll();
    DoneGoals save(DoneGoals entity, long userId);
    DoneGoals getById(long id);
}

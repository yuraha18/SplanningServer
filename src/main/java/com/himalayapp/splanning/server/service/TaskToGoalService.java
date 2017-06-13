package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.TaskToGoal;

import java.util.List;

public interface TaskToGoalService {
    List<TaskToGoal> getAll();
    TaskToGoal save(TaskToGoal entity, long userId);
    void remove(long id, long userId);
    TaskToGoal getById(long id);
}

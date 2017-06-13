package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.DoneTask;

import java.util.List;

public interface DoneTasksService {
    List<DoneTask> getAll();
    DoneTask save(DoneTask enitity, long userId);
    void remove(long id, long userId);
    DoneTask getById(long id);
}

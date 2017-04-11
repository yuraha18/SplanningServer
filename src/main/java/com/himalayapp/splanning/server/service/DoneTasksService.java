package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.DoneTasks;

import java.util.List;

public interface DoneTasksService {
    List<DoneTasks> getAll();
    DoneTasks save(DoneTasks enitity, long userId);
    void remove(long id);
    DoneTasks getById(long id);
}

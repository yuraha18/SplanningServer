package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();
    Task getByID(long id);
    Task save(Task remind);
    void remove(long id);
}

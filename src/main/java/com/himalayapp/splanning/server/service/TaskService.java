package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();
    Task save(Task remind, long userId);
    Task getById(long id);
}

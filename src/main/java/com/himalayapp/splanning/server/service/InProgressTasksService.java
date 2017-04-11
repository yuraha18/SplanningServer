package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.InProgressTasks;

import java.util.List;

public interface InProgressTasksService {
    List<InProgressTasks> getAll();
    InProgressTasks save(InProgressTasks entity, long userId);
    InProgressTasks getById(long id);
}

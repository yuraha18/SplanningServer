package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Days;
import com.himalayapp.splanning.server.entity.DeletedTasks;

import java.util.List;

public interface DeletedTasksService {
    List<DeletedTasks> getAll();
    DeletedTasks save(DeletedTasks entity, long userId);
    DeletedTasks getById(long id);
}

package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.TaskLifecycle;

import java.util.List;

public interface TaskLifecycleService {
    List<TaskLifecycle> getAll();
    TaskLifecycle save(TaskLifecycle entity, long userId);
    TaskLifecycle getById(long id);
}

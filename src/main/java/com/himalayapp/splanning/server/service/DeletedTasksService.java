package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.DeletedTask;

import java.util.List;

public interface DeletedTasksService {
    List<DeletedTask> getAll();
    DeletedTask save(DeletedTask entity, long userId);
    DeletedTask getById(long id);
}

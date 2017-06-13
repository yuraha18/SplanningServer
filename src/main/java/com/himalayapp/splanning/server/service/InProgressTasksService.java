package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.InProgressTask;

import java.util.List;

public interface InProgressTasksService {
    List<InProgressTask> getAll();
    InProgressTask save(InProgressTask entity, long userId);
    InProgressTask getById(long id);
}

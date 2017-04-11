package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.TaskLifecycle;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.repository.TaskLifecycleRepository;
import com.himalayapp.splanning.server.service.TaskLifecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskLifecycleServiceImpl implements TaskLifecycleService {

    @Autowired
    private TaskLifecycleRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<TaskLifecycle> getAll() {
        return repository.findAll();
    }

    public TaskLifecycle save(TaskLifecycle enitity, long userId) {
        TaskLifecycle newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.TASK_LIFECYCLE_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public TaskLifecycle getById(long id) {
        return repository.findOne(id);
    }

}

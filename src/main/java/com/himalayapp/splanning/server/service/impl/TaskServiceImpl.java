package com.himalayapp.splanning.server.service.impl;


import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.Synchronizer;
import com.himalayapp.splanning.server.entity.Task;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.repository.TaskRepository;
import com.himalayapp.splanning.server.service.SynchronizerService;
import com.himalayapp.splanning.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private  SynchronizerRepository sr;

    public List<Task> getAll() {
        return repository.findAll();
    }


    public Task save(Task task, long userId) {
        Task newTask = repository.saveAndFlush(task);
        int tableId = Constants.dbTables.get(Constants.TASK_TABLE);

        sr.saveAndFlush(Synchronization.getSynchronizer(newTask.getId(), tableId, userId));
        return newTask;
    }

    public Task getById(long id) {
        return repository.findOne(id);
    }



}

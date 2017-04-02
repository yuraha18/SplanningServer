package com.himalayapp.splanning.server.service.impl;


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
    private TaskRepository taskRepositiry;

    @Autowired
    private SynchronizerRepository sr;

    public List<Task> getAll() {
        return taskRepositiry.findAll();
    }

    public Task getByID(long id) {
        return taskRepositiry.findOne(id);
    }

    public Task save(Task task) {
        Task newTask = taskRepositiry.saveAndFlush(task);
        System.out.println(newTask);
        Synchronizer synchronizer = getSynchronizer(newTask, 1);

        sr.saveAndFlush(synchronizer);
        return newTask;
    }

    private Synchronizer getSynchronizer(Task newTask, int tableId) {
        Synchronizer synch = new Synchronizer();
        synch.setRowId(newTask.getId());
        synch.setTableId(tableId);
        synch.setUpdateTime(System.currentTimeMillis());

        return synch;
    }

    public void remove(long id) {
       taskRepositiry.delete(id);
    }
}

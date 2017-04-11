package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.TaskToGoal;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.repository.TaskToGoalRepository;
import com.himalayapp.splanning.server.service.TaskToGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskToGoalServiceImpl implements TaskToGoalService {

    @Autowired
    private TaskToGoalRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<TaskToGoal> getAll() {
        return repository.findAll();
    }

    public TaskToGoal save(TaskToGoal enitity, long userId) {
        TaskToGoal newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.TASK_TO_GOAL_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public TaskToGoal getById(long id) {
        return repository.findOne(id);
    }
}

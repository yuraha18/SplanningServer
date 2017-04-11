package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Task;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.DoneTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoneTasksServiceImpl implements DoneTasksService{

    @Autowired
    private DoneTasksRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<DoneTasks> getAll() {
        return repository.findAll();
    }

    public DoneTasks save(DoneTasks enitity, long userId) {
        DoneTasks newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DONE_TASKS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id) {
     repository.delete(id);
    }

    public DoneTasks getById(long id) {
        return repository.findOne(id);
    }
}

package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTask;
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

    public List<DoneTask> getAll() {
        return repository.findAll();
    }

    public DoneTask save(DoneTask enitity, long userId) {
        DoneTask newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DONE_TASKS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id, long userId) {
        if (repository.exists(id)) {
            repository.delete(id);
            int tableId = Constants.dbTables.get(Constants.DONE_TASKS_TABLE);
            sr.saveAndFlush(Synchronization.getSynchronizer(id, tableId, userId));
        }
    }


    public DoneTask getById(long id) {
        return repository.findOne(id);
    }
}

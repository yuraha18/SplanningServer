package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DeletedTasks;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.repository.DeletedTasksRepository;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.DeletedTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeletedTasksServiceImpl implements DeletedTasksService {

    @Autowired
    private DeletedTasksRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<DeletedTasks> getAll() {
        return repository.findAll();
    }

    public DeletedTasks save(DeletedTasks enitity, long userId) {
        DeletedTasks newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DELETED_TASKS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public DeletedTasks getById(long id) {
        return repository.findOne(id);
    }
}

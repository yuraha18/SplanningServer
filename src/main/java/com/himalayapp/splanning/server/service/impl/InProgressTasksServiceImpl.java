package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.InProgressTask;
import com.himalayapp.splanning.server.repository.InProgressTasksRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.InProgressTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InProgressTasksServiceImpl  implements InProgressTasksService{


    @Autowired
    private InProgressTasksRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<InProgressTask> getAll() {
        return repository.findAll();
    }

    public InProgressTask save(InProgressTask enitity, long userId) {
        InProgressTask newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.IN_PROGRESS_TASKS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public InProgressTask getById(long id) {
        return repository.findOne(id);
    }
}

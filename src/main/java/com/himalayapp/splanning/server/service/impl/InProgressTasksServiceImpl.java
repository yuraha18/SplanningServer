package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.InProgressTasks;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
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

    public List<InProgressTasks> getAll() {
        return repository.findAll();
    }

    public InProgressTasks save(InProgressTasks enitity, long userId) {
        InProgressTasks newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.IN_PROGRESS_TASKS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public InProgressTasks getById(long id) {
        return repository.findOne(id);
    }
}

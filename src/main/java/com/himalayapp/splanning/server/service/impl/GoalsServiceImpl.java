package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Goals;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.GoalsRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalsServiceImpl implements GoalsService {

    @Autowired
    private GoalsRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<Goals> getAll() {
        return repository.findAll();
    }

    public Goals save(Goals enitity, long userId) {
        Goals newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.GOALS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public Goals getById(long id) {
        return repository.findOne(id);
    }
}

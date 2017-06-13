package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.Goal;
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

    public List<Goal> getAll() {
        return repository.findAll();
    }

    public Goal save(Goal enitity, long userId) {
        System.out.println("save " + enitity);
        Goal newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.GOALS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id, long userId) {
        System.out.println("remove goal "+id) ;
        if (repository.exists(id)) {
            repository.delete(id);
            int tableId = Constants.dbTables.get(Constants.GOALS_TABLE);
            sr.saveAndFlush(Synchronization.getSynchronizer(id, tableId, userId));
        }
    }

    public Goal getById(long id) {
        return repository.findOne(id);
    }
}

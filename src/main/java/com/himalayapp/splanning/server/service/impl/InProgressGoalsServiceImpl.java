package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.InProgressGoal;
import com.himalayapp.splanning.server.repository.InProgressGoalsRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.InProgressGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InProgressGoalsServiceImpl implements InProgressGoalsService {

    @Autowired
    private InProgressGoalsRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<InProgressGoal> getAll() {
        return repository.findAll();
    }

    public InProgressGoal save(InProgressGoal enitity, long userId) {

        List<InProgressGoal> existRows = repository.findEqualItems(enitity.getGoalId());
        if (existRows.isEmpty()) {
            InProgressGoal newEntity = repository.saveAndFlush(enitity);

            int tableId = Constants.dbTables.get(Constants.IN_PROGRESS_GOALS_TABLE);
            sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
            return newEntity;
        }

        return existRows.get(0);
    }

    public void remove(long id, long userId) {

        if (repository.exists(id)) {
            repository.delete(id);
            int tableId = Constants.dbTables.get(Constants.IN_PROGRESS_GOALS_TABLE);
            sr.saveAndFlush(Synchronization.getSynchronizer(id, tableId, userId));
        }
    }

    public InProgressGoal getById(long id) {
        return repository.findOne(id);
    }
}

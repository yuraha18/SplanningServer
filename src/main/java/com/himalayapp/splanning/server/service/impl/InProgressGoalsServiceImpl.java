package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.InProgressGoals;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
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

    public List<InProgressGoals> getAll() {
        return repository.findAll();
    }

    public InProgressGoals save(InProgressGoals enitity, long userId) {
        InProgressGoals newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.IN_PROGRESS_GOALS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public InProgressGoals getById(long id) {
        return repository.findOne(id);
    }
}

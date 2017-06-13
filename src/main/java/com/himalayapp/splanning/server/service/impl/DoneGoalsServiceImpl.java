package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneGoal;
import com.himalayapp.splanning.server.repository.DoneGoalsRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.DoneGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoneGoalsServiceImpl implements DoneGoalsService {

    @Autowired
    private DoneGoalsRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<DoneGoal> getAll() {
        return repository.findAll();
    }

    public DoneGoal save(DoneGoal enitity, long userId) {
        System.out.println("save " + enitity);
        DoneGoal newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DONE_GOALS_TABLE);

        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public DoneGoal getById(long id) {
        return repository.findOne(id);
    }

}

package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneGoals;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.repository.DoneGoalsRepository;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
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

    public List<DoneGoals> getAll() {
        return repository.findAll();
    }

    public DoneGoals save(DoneGoals enitity, long userId) {
        DoneGoals newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DONE_GOALS_TABLE);

        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public DoneGoals getById(long id) {
        return repository.findOne(id);
    }

}

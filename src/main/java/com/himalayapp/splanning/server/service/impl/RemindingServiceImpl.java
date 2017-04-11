package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Reminding;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.RemindingRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.RemindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemindingServiceImpl implements RemindingService {

    @Autowired
    private RemindingRepository repository;

    @Autowired
    private SynchronizerRepository sr;


    public List<Reminding> getAll() {
        return repository.findAll();
    }

    public Reminding save(Reminding enitity, long userId) {
        Reminding newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.REMINDING_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public Reminding getById(long id) {
        return repository.findOne(id);
    }
}

package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Repeating;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.RepeatingRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.RepeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepeatingServiceImpl implements RepeatingService {

    @Autowired
    private RepeatingRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<Repeating> getAll() {
        return repository.findAll();
    }

    public Repeating save(Repeating enitity, long userId) {
        Repeating newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.REPEATING_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public Repeating getById(long id) {
        return repository.findOne(id);
    }
}
package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.MonthRepeating;
import com.himalayapp.splanning.server.repository.MonthRepeatingRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.MonthRepeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonthRepeatingServiceImpl  implements MonthRepeatingService{

    @Autowired
    private MonthRepeatingRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<MonthRepeating> getAll() {
        return repository.findAll();
    }

    public MonthRepeating save(MonthRepeating enitity, long userId) {
        MonthRepeating newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.MONTH_REPEATING_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id, long userId) {
        if (repository.exists(id)) {
            repository.delete(id);
            int tableId = Constants.dbTables.get(Constants.MONTH_REPEATING_TABLE);
            sr.saveAndFlush(Synchronization.getSynchronizer(id, tableId, userId));
        }
    }

    public MonthRepeating getById(long id) {
        return repository.findOne(id);
    }
}

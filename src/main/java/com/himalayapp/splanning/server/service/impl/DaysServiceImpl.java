package com.himalayapp.splanning.server.service.impl;


import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.Days;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.repository.DaysRepository;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.DaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DaysServiceImpl implements DaysService {

    @Autowired
    private DaysRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<Days> getAll() {
        return repository.findAll();
    }

    public Days save(Days enitity, long userId) {
        String day = enitity.getText();
       Days newDay = repository.getDay(day);

       if (newDay!=null)
       return newDay;

        Days newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DAYS_TABLE);

        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public Days getById(long id) {
        return repository.findOne(id);
    }


}

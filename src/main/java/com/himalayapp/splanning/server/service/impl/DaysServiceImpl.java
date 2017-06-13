package com.himalayapp.splanning.server.service.impl;


import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.Day;
import com.himalayapp.splanning.server.repository.DaysRepository;
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

    public List<Day> getAll() {
        return repository.findAll();
    }

    public Day save(Day enitity, long userId) {
        String day = enitity.getText();

      // Day newDay = repository.getDay(day);
        Day   newEntity;

     //  if (newDay!=null)
         //  newEntity= newDay;

      // else
            newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.DAYS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public Day getById(long id) {
       Day day = repository.findOne(id);
        return day;
    }


}

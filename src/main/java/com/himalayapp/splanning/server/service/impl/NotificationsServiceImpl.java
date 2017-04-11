package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Notifications;
import com.himalayapp.splanning.server.repository.DoneTasksRepository;
import com.himalayapp.splanning.server.repository.NotificationsRepository;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    @Autowired
    private NotificationsRepository repository;

    @Autowired
    private SynchronizerRepository sr;

    public List<Notifications> getAll() {
        return repository.findAll();
    }

    public Notifications save(Notifications enitity, long userId) {
        Notifications newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.NOTIFICATIONS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id) {
        repository.delete(id);
    }

    public Notifications getById(long id) {
        return repository.findOne(id);
    }
}

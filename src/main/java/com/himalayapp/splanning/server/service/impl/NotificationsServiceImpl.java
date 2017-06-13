package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.Synchronization;
import com.himalayapp.splanning.server.entity.Notification;
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

    public List<Notification> getAll() {
        return repository.findAll();
    }

    public Notification save(Notification enitity, long userId) {
        System.out.println("save notif" + enitity);
        Notification newEntity = repository.saveAndFlush(enitity);

        int tableId = Constants.dbTables.get(Constants.NOTIFICATIONS_TABLE);
        sr.saveAndFlush(Synchronization.getSynchronizer(newEntity.getId(), tableId, userId));
        return newEntity;
    }

    public void remove(long id, long userId) {
        if (repository.exists(id)) {
            repository.delete(id);
            int tableId = Constants.dbTables.get(Constants.NOTIFICATIONS_TABLE);
            sr.saveAndFlush(Synchronization.getSynchronizer(id, tableId, userId));
        }
    }

    public Notification getById(long id) {
        return repository.findOne(id);
    }
}

package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Notification;

import java.util.List;

public interface NotificationsService {
    List<Notification> getAll();
    Notification save(Notification entity, long userId);
    void remove(long id, long userId);
    Notification getById(long id);
}

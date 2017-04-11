package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Notifications;

import java.util.List;

public interface NotificationsService {
    List<Notifications> getAll();
    Notifications save(Notifications entity, long userId);
    void remove(long id);
    Notifications getById(long id);
}

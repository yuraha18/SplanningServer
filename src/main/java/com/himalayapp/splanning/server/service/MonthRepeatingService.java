package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.MonthRepeating;

import java.util.List;

public interface MonthRepeatingService {
    List<MonthRepeating> getAll();
    MonthRepeating save(MonthRepeating entity, long userId);
    void remove(long id);
    MonthRepeating getById(long id);
}

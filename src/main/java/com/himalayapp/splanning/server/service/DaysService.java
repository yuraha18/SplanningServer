package com.himalayapp.splanning.server.service;

import com.himalayapp.splanning.server.entity.Day;

import java.util.List;

public interface DaysService {
    List<Day> getAll();
    Day save(Day entity, long userId);
    Day getById(long id);
}

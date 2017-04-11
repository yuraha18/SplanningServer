package com.himalayapp.splanning.server.service;

import com.himalayapp.splanning.server.entity.Days;

import java.util.List;

public interface DaysService {
    List<Days> getAll();
    Days save(Days entity, long userId);
    Days getById(long id);
}

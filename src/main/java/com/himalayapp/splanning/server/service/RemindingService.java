package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.Reminding;

import java.util.List;

public interface RemindingService {
    List<Reminding> getAll();
    Reminding save(Reminding entity, long userId);
    Reminding getById(long id);
}

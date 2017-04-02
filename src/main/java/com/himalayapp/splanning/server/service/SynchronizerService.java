package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.SynchObject;
import com.himalayapp.splanning.server.entity.Synchronizer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SynchronizerService {

    List<SynchObject> getObjectsForSynch(long userId, long time);
}

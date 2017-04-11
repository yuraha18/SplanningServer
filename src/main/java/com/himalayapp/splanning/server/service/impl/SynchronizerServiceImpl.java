package com.himalayapp.splanning.server.service.impl;

import com.himalayapp.splanning.server.entity.SynchObject;
import com.himalayapp.splanning.server.entity.Synchronizer;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.SynchronizerService;
import org.hibernate.Query;
import org.hibernate.jpa.internal.EntityManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class SynchronizerServiceImpl implements SynchronizerService {

    @Autowired
    private SynchronizerRepository rep;

    public List<SynchObject> getObjectsForSynch(long userId, long lastUpdateTime) {

        return convertFromSynchronizer( rep.findNewRows(userId,lastUpdateTime));
    }

    private List<SynchObject> convertFromSynchronizer(List<Synchronizer> synchronizers) {
        List<SynchObject> synchObjects = new ArrayList<SynchObject>();

        for (Synchronizer synch : synchronizers)
        {
            SynchObject synchObject = new SynchObject();
            synchObject.setId(synch.getId());
            synchObject.setRowId(synch.getRowId());
            synchObject.setTableId(synch.getTableId());

            synchObjects.add(synchObject);
        }

        return synchObjects;
    }

}

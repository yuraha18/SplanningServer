package com.himalayapp.splanning.server;


import com.himalayapp.splanning.server.entity.Synchronizer;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.SynchronizerService;
import org.springframework.beans.factory.annotation.Autowired;

public class Synchronization {

    @Autowired
    private static SynchronizerRepository sr;

    public static Synchronizer getSynchronizer(long rowId, int tableId, long userId) {
        Synchronizer synch = new Synchronizer();
        synch.setUserId(userId);
        synch.setRowId(rowId);
        synch.setTableId(tableId);
        synch.setUpdateTime(System.currentTimeMillis());

        return synch;
    }

    public static void addToSynchTable(long rowId, int tableId, long userId)
    {
        Synchronizer synchronizer = getSynchronizer(rowId, tableId, userId);
        System.out.println("addToSynchTable");
        sr.saveAndFlush(synchronizer);
        System.out.println("addToSynchTable");
    }
}

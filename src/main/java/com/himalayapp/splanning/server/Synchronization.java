package com.himalayapp.splanning.server;


import com.himalayapp.splanning.server.entity.*;
import com.himalayapp.splanning.server.repository.SynchronizerRepository;
import com.himalayapp.splanning.server.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Synchronization {

    @Autowired SynchronizerRepository sr;


    public static Synchronizer getSynchronizer(long rowId, int tableId, long userId) {
        Synchronizer synch = new Synchronizer();
        synch.setUserId(userId);
        synch.setRowId(rowId);
        synch.setTableId(tableId);
        synch.setUpdateTime(System.currentTimeMillis());

        return synch;
    }

    public  void addToSynchTable(long rowId, int tableId, long userId)
    {
        Synchronizer synchronizer = getSynchronizer(rowId, tableId, userId);
        sr.saveAndFlush(synchronizer);
    }





    ////////////////////////////////////////////////////////////////////////////////////

}

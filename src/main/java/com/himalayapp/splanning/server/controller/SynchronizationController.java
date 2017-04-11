package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.URL;
import com.himalayapp.splanning.server.entity.SynchObject;

import com.himalayapp.splanning.server.service.SynchronizerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SynchronizationController {

    @Autowired
    private SynchronizerService synchServise;

    @RequestMapping(value = URL.GET_SYNCH_DATA, method = RequestMethod.GET)
    @ResponseBody
    public List<SynchObject> getSynchObjects(@RequestParam("userId") long userId, @RequestParam("time") long time) {
        System.out.println(time);
        List<SynchObject> list = synchServise.getObjectsForSynch(userId, time);
        for(SynchObject object : list)
            System.out.println(object);
        System.out.println("nothing for synch");

        return list;
    }
}

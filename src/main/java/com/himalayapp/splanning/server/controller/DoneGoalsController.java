package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneGoals;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.service.DoneGoalsService;
import com.himalayapp.splanning.server.service.DoneTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoneGoalsController {

    @Autowired
    private DoneGoalsService service;

    @RequestMapping(value = Constants.DONE_GOALS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public DoneGoals save(@RequestBody DoneGoals entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DONE_GOALS_URL, method = RequestMethod.GET)
    public List<DoneGoals> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DONE_GOALS_URL+"/{id}", method = RequestMethod.GET)
    public DoneGoals getById(@PathVariable long id) {
        return service.getById(id);
    }

}

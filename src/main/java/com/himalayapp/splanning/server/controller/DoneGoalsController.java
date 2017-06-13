package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneGoal;
import com.himalayapp.splanning.server.service.DoneGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoneGoalsController {

    @Autowired
    private DoneGoalsService service;

    @RequestMapping(value = Constants.DONE_GOALS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public DoneGoal save(@RequestBody DoneGoal entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DONE_GOALS_URL, method = RequestMethod.GET)
    public List<DoneGoal> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DONE_GOALS_URL+"/{id}", method = RequestMethod.GET)
    public DoneGoal getById(@PathVariable long id) {
        return service.getById(id);
    }

}

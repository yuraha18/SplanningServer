package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.InProgressGoal;
import com.himalayapp.splanning.server.service.InProgressGoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InProgressGoalsController  {

    @Autowired
    private InProgressGoalsService service;


    @RequestMapping(value = Constants.IN_PROGRESS_GOALS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public InProgressGoal save(@RequestBody InProgressGoal entity, @PathVariable long userId) {
        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.IN_PROGRESS_GOALS_URL, method = RequestMethod.GET)
    public List<InProgressGoal> loadAll() {
        return service.getAll();
    }

  /*  @RequestMapping(value = Constants.IN_PROGRESS_GOALS_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }*/

    @RequestMapping(value = Constants.IN_PROGRESS_GOALS_URL+"/{id}", method = RequestMethod.GET)
    public InProgressGoal getById(@PathVariable long id) {
        return service.getById(id);
    }

}

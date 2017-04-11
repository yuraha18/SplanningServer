package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Goals;
import com.himalayapp.splanning.server.service.DoneTasksService;
import com.himalayapp.splanning.server.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalsController {

    @Autowired
    private GoalsService service;


    @RequestMapping(value = Constants.GOALS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Goals save(@RequestBody Goals entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.GOALS_URL, method = RequestMethod.GET)
    public List<Goals> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.GOALS_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = Constants.GOALS_URL+"/{id}", method = RequestMethod.GET)
    public Goals getById(@PathVariable long id) {

        return service.getById(id);
    }
}

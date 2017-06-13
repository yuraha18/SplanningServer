package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.Goal;
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
    public Goal save(@RequestBody Goal entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.GOALS_URL, method = RequestMethod.GET)
    public List<Goal> loadAll() {
        return service.getAll();
    }

   /* @RequestMapping(value = Constants.GOALS_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }*/

    @RequestMapping(value = Constants.GOALS_URL+"/{id}", method = RequestMethod.GET)
    public Goal getById(@PathVariable long id) {

        return service.getById(id);
    }
}

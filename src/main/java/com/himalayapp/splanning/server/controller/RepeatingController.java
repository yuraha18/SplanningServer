package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Repeating;
import com.himalayapp.splanning.server.service.DoneTasksService;
import com.himalayapp.splanning.server.service.RepeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RepeatingController {

    @Autowired
    private RepeatingService service;


    @RequestMapping(value = Constants.REPEATING_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Repeating save(@RequestBody Repeating entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());
        System.out.println("repeating");
        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.REPEATING_URL, method = RequestMethod.GET)
    public List<Repeating> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.REPEATING_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = Constants.REPEATING_URL+"/{id}", method = RequestMethod.GET)
    public Repeating getById(@PathVariable long id) {
        return service.getById(id);
    }
}

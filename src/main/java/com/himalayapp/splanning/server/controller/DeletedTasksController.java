package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DeletedTask;
import com.himalayapp.splanning.server.service.DeletedTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeletedTasksController {

    @Autowired
    private DeletedTasksService service;


    @RequestMapping(value = Constants.DELETED_TASKS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public DeletedTask save(@RequestBody DeletedTask entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DELETED_TASKS_URL, method = RequestMethod.GET)
    public List<DeletedTask> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DELETED_TASKS_URL+"/{id}", method = RequestMethod.GET)
    public DeletedTask getById(@PathVariable long id) {
        return service.getById(id);
    }

}

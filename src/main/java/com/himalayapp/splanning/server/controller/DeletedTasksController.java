package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DeletedTasks;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.service.DeletedTasksService;
import com.himalayapp.splanning.server.service.DoneTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeletedTasksController {

    @Autowired
    private DeletedTasksService service;


    @RequestMapping(value = Constants.DELETED_TASKS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public DeletedTasks save(@RequestBody DeletedTasks entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DELETED_TASKS_URL, method = RequestMethod.GET)
    public List<DeletedTasks> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DELETED_TASKS_URL+"/{id}", method = RequestMethod.GET)
    public DeletedTasks getById(@PathVariable long id) {
        return service.getById(id);
    }

}

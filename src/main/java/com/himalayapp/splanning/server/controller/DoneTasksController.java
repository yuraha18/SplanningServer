package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.URL;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.Task;
import com.himalayapp.splanning.server.service.DoneTasksService;
import com.himalayapp.splanning.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoneTasksController {

    @Autowired
    private DoneTasksService service;


    @RequestMapping(value = Constants.DONE_TASKS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public DoneTasks save(@RequestBody DoneTasks entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DONE_TASKS_URL, method = RequestMethod.GET)
    public List<DoneTasks> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DONE_TASKS_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = Constants.DONE_TASKS_URL+"/{id}", method = RequestMethod.GET)
    public DoneTasks getById(@PathVariable long id) {
        return service.getById(id);
    }
}

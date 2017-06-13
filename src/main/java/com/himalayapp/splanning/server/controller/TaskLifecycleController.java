package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.TaskLifecycle;
import com.himalayapp.splanning.server.service.TaskLifecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskLifecycleController {

    @Autowired
    private TaskLifecycleService service;


    @RequestMapping(value = Constants.TASK_LIFECYCLE_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public TaskLifecycle save(@RequestBody TaskLifecycle entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.TASK_LIFECYCLE_URL, method = RequestMethod.GET)
    public List<TaskLifecycle> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.TASK_LIFECYCLE_URL+"/{id}", method = RequestMethod.GET)
    public TaskLifecycle getById(@PathVariable long id) {
        return service.getById(id);
    }

}

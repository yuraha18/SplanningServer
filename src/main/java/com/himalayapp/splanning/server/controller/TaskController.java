package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.URL;
import com.himalayapp.splanning.server.entity.Task;
import com.himalayapp.splanning.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {

   @Autowired
   private TaskService service;

    @RequestMapping(value = Constants.TASK_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Task saveTask(@RequestBody Task task,  @PathVariable long userId) {
        System.out.println("save" + task.toString());

        return service.save(task, userId);
    }

    @RequestMapping(value = Constants.TASK_URL, method = RequestMethod.GET)
    public List<Task> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.TASK_URL+"/{id}", method = RequestMethod.GET)
    public Task getById(@PathVariable long id) {
        return service.getById(id);
    }


}

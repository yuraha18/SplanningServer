package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.URL;
import com.himalayapp.splanning.server.entity.Task;
import com.himalayapp.splanning.server.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class TaskController {

   @Autowired
   private TaskService taskService;

    @RequestMapping(value = URL.TASK + "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Task getTask(@PathVariable long id) {

        return taskService.getByID(id);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseBody
    public Task saveRemider(@RequestBody Task task) {
        System.out.println("save" + task.toString());

        return taskService.save(task);
    }


    @RequestMapping(value = URL.TASK, method = RequestMethod.GET)
    public List<Task> loadAll() {
        return taskService.getAll();
    }


}

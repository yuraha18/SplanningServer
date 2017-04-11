package com.himalayapp.splanning.server.controller;


import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.TaskToGoal;
import com.himalayapp.splanning.server.service.DoneTasksService;
import com.himalayapp.splanning.server.service.TaskToGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskToGoalController {

    @Autowired
    private TaskToGoalService service;

    @RequestMapping(value = Constants.TASK_TO_GOAL_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public TaskToGoal save(@RequestBody TaskToGoal entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.TASK_TO_GOAL_URL, method = RequestMethod.GET)
    public List<TaskToGoal> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.TASK_TO_GOAL_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = Constants.TASK_TO_GOAL_URL+"/{id}", method = RequestMethod.GET)
    public TaskToGoal getById(@PathVariable long id) {
        return service.getById(id);
    }
}

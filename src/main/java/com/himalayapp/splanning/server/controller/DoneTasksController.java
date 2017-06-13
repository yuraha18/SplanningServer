package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneTask;
import com.himalayapp.splanning.server.service.DoneTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoneTasksController {

    @Autowired
    private DoneTasksService service;


    @RequestMapping(value = Constants.DONE_TASKS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public DoneTask save(@RequestBody DoneTask entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DONE_TASKS_URL, method = RequestMethod.GET)
    public List<DoneTask> loadAll() {
        return service.getAll();
    }

   /* @RequestMapping(value = Constants.DONE_TASKS_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }*/

    @RequestMapping(value = Constants.DONE_TASKS_URL+"/{id}", method = RequestMethod.GET)
    public DoneTask getById(@PathVariable long id) {
        return service.getById(id);
    }
}

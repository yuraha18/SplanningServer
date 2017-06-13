package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.InProgressTask;
import com.himalayapp.splanning.server.service.InProgressTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InProgressTasksConroller {

    @Autowired
    private InProgressTasksService service;


    @RequestMapping(value = Constants.IN_PROGRESS_TASKS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public InProgressTask save(@RequestBody InProgressTask entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.IN_PROGRESS_TASKS_URL, method = RequestMethod.GET)
    public List<InProgressTask> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.IN_PROGRESS_TASKS_URL+"/{id}", method = RequestMethod.GET)
    public InProgressTask getById(@PathVariable long id) {
        return service.getById(id);
    }

}

package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.Days;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.service.DaysService;
import com.himalayapp.splanning.server.service.DoneTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DaysController {

    @Autowired
    private DaysService service;


    @RequestMapping(value = Constants.DAYS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Days save(@RequestBody Days entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DAYS_URL, method = RequestMethod.GET)
    public List<Days> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DAYS_URL+"/{id}", method = RequestMethod.GET)
    public Days getById(@PathVariable long id) {
      return service.getById(id);
    }

}

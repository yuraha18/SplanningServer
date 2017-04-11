package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.DoneTasks;
import com.himalayapp.splanning.server.entity.MonthRepeating;
import com.himalayapp.splanning.server.service.DoneTasksService;
import com.himalayapp.splanning.server.service.MonthRepeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MonthRepeatingController {

    @Autowired
    private MonthRepeatingService service;


    @RequestMapping(value = Constants.MONTH_REPEATING_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public MonthRepeating save(@RequestBody MonthRepeating entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }


    @RequestMapping(value = Constants.MONTH_REPEATING_URL, method = RequestMethod.GET)
    public List<MonthRepeating> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.MONTH_REPEATING_URL+"/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }

    @RequestMapping(value = Constants.MONTH_REPEATING_URL+"/{id}", method = RequestMethod.GET)
    public MonthRepeating getById(@PathVariable long id) {
        return service.getById(id);
    }

}

package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.Day;
import com.himalayapp.splanning.server.service.DaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DaysController {

    @Autowired
    private DaysService service;


    @RequestMapping(value = Constants.DAYS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Day save(@RequestBody Day entity, @PathVariable long userId) {
        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.DAYS_URL, method = RequestMethod.GET)
    public List<Day> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.DAYS_URL+"/{id}", method = RequestMethod.GET)
    public Day getById(@PathVariable long id) {
      return service.getById(id);
    }

}

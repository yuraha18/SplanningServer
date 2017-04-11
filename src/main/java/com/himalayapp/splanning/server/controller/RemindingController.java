package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.Reminding;
import com.himalayapp.splanning.server.service.RemindingService;
import com.himalayapp.splanning.server.service.RepeatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RemindingController {

   @Autowired
   private RemindingService service;


    @RequestMapping(value = Constants.REMINDING_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Reminding save(@RequestBody Reminding entity, @PathVariable long userId) {
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.REMINDING_URL, method = RequestMethod.GET)
    public List<Reminding> loadAll() {
        return service.getAll();
    }

    @RequestMapping(value = Constants.REMINDING_URL+"/{id}", method = RequestMethod.GET)
    public Reminding getById(@PathVariable long id) {
        return service.getById(id);
    }

}

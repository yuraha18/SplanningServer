package com.himalayapp.splanning.server.controller;


import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.Notification;
import com.himalayapp.splanning.server.service.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationsController {

    @Autowired
    private NotificationsService service;

    @RequestMapping(value = Constants.NOTIFICATIONS_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public Notification save(@RequestBody Notification entity, @PathVariable long userId) {
        System.out.println("save notification");
        System.out.println("save" + entity.toString());

        return service.save(entity, userId);
    }

    @RequestMapping(value = Constants.NOTIFICATIONS_URL, method = RequestMethod.GET)
    public List<Notification> loadAll() {
        return service.getAll();
    }

   /* @RequestMapping(value = Constants.NOTIFICATIONS_URL + "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id) {
        service.remove(id);
    }*/

    @RequestMapping(value = Constants.NOTIFICATIONS_URL+"/{id}", method = RequestMethod.GET)
    public Notification getById(@PathVariable long id) {
        return service.getById(id);
    }
}

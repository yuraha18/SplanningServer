package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.URL;
import com.himalayapp.splanning.server.entity.Time;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TimeController {

    @RequestMapping(value = URL.CURRENT_TIME, method = RequestMethod.GET)
    public Time getTime()
    {
        Time currentTime = new Time();
        currentTime.setCurrentTime(System.currentTimeMillis());
        System.out.println("getTime");
        System.out.println(currentTime);
        return currentTime;
    }
}

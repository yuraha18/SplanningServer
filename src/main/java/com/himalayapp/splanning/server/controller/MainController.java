package com.himalayapp.splanning.server.controller;

import com.himalayapp.splanning.server.Constants;
import com.himalayapp.splanning.server.entity.*;
import com.himalayapp.splanning.server.service.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {

    @Autowired private MainService mainService;

    @RequestMapping(value = Constants.SAVE_LIST_URL+"/{userId}", method = RequestMethod.POST)
    @ResponseBody
    public MainEntity save(@RequestBody MainEntity mainEntity, @PathVariable long userId) throws SQLException {
        System.out.println("request: " + mainEntity);
        MainEntity newEntity = mainService.synchWithApp(mainEntity, userId);// запускаю метод для сохранения данных
        return newEntity;
    }
}

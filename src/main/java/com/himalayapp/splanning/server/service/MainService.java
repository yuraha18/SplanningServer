package com.himalayapp.splanning.server.service;


import com.himalayapp.splanning.server.entity.MainEntity;

public interface MainService {
    MainEntity synchWithApp(MainEntity request, long userId);
}

package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.DoneTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoneTasksRepository extends JpaRepository<DoneTask, Long> {
}

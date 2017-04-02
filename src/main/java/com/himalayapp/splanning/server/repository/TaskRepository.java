package com.himalayapp.splanning.server.repository;

import com.himalayapp.splanning.server.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}

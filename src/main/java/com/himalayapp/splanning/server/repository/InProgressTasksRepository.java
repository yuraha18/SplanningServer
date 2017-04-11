package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.InProgressTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InProgressTasksRepository extends JpaRepository<InProgressTasks, Long> {
}

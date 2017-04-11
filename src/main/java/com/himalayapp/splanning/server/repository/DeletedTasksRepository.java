package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.DeletedTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedTasksRepository extends JpaRepository<DeletedTasks, Long> {
}

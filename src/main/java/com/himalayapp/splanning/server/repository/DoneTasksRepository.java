package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.DoneTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoneTasksRepository extends JpaRepository<DoneTasks, Long> {
}

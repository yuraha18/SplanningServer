package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.InProgressGoals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InProgressGoalsRepository extends JpaRepository<InProgressGoals, Long> {
}

package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.InProgressGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InProgressGoalsRepository extends JpaRepository<InProgressGoal, Long> {

    @Query(value = "SELECT * FROM InProgressGoal WHERE goal_id = ?1", nativeQuery = true)
    List<InProgressGoal> findEqualItems(long goalId);
}

package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.DoneGoal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoneGoalsRepository extends JpaRepository<DoneGoal, Long> {
}

package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.Goals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalsRepository extends JpaRepository<Goals, Long> {
}

package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DaysRepository extends JpaRepository<Day, Long> {

    @Query(value = "SELECT id, text FROM days WHERE text = ?1", nativeQuery = true)
    Day getDay(String text);
}

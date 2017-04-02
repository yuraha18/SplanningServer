package com.himalayapp.splanning.server.repository;



import com.himalayapp.splanning.server.entity.Synchronizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SynchronizerRepository extends JpaRepository<Synchronizer, Long>{

    @Query(value = "SELECT * FROM synchronizer WHERE user_id = ?1 AND update_time > ?2", nativeQuery = true)
    List<Synchronizer> findNewRows(long userId,  long time);
}

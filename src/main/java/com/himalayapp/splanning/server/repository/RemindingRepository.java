package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.Reminding;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RemindingRepository extends JpaRepository<Reminding, Long> {
}

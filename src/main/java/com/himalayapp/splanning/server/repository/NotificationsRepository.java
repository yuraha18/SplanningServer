package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
}

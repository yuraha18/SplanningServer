package com.himalayapp.splanning.server.repository;


import com.himalayapp.splanning.server.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notification, Long> {
}

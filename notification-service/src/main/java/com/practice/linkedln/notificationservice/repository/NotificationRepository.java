package com.practice.linkedln.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.linkedln.notificationservice.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}


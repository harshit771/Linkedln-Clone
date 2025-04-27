package com.practice.linkedln.notificationservice.service.impl;

import org.springframework.stereotype.Service;

import com.practice.linkedln.notificationservice.entity.Notification;
import com.practice.linkedln.notificationservice.repository.NotificationRepository;
import com.practice.linkedln.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private final NotificationRepository notificationRepository;

    @Override
    public void addNotification(Notification notification) {
        log.info("Adding notification in DB");
        notification =  notificationRepository.save(notification);

    }

}

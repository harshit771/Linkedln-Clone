package com.practice.linkedln.notificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.practice.linkedln.ConnectionsService.event.RequestAcceptEvent;
import com.practice.linkedln.ConnectionsService.event.RequestRejectEvent;
import com.practice.linkedln.ConnectionsService.event.RequestSendEvent;
import com.practice.linkedln.notificationservice.entity.Notification;
import com.practice.linkedln.notificationservice.service.NotificationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "request_send_topic")
    public void handleSendRequest(RequestSendEvent requestSendEvent){
        log.info("Sending send request notification");

        String message = String.format("%d sent you a connection request", requestSendEvent.getSenderUserId());

        Notification notification = Notification.builder().userId(requestSendEvent.getReceiverUserId())
                                    .message(message).build();
        notificationService.addNotification(notification);
        log.info("connection request notification send successfully...");
    }

    @KafkaListener(topics = "request_accept_topic")
    public void handleAcceptRequest(RequestAcceptEvent requestAcceptEvent){
        log.info("Send accept notification");
        
        String message = String.format("%d accepted your request ", requestAcceptEvent.getAcceptUserId());

        Notification notification = Notification.builder().message(message)
                                        .userId(requestAcceptEvent.getRequestedUserId()).build();


        notificationService.addNotification(notification);

        log.info("connection accepted notification send successfully...");
    }   
    
    @KafkaListener(topics = "request_reject_topic")
    public void handleRejectRequest(RequestRejectEvent requestRejectEvent){
        log.info("Send reject notification");

        String message = String.format("%d rejected your request", requestRejectEvent.getReceiverUserId());

        Notification notification = Notification.builder().message(message)
                                    .userId(requestRejectEvent.getRejectUserId()).build();

        notificationService.addNotification(notification);

        log.info("connection rejected notification send successfully...");
    }

}

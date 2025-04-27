package com.practice.linkedln.notificationservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.practice.linkedln.notificationservice.entity.Notification;
import com.practice.linkedln.notificationservice.service.NotificationService;
import com.practice.linkedln.postService.event.PostCreatedEvent;
import com.practice.linkedln.postService.event.PostLikedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostConsumer {


    private final NotificationService notificationService;


    @KafkaListener(topics = "post_created_topic") 
    public void handlePostCreated(PostCreatedEvent postCreatedEvent){
        log.info("inside handlePostCreated method");
        String message = String.format("Your connection with id %d has created this post: %s", 
                    postCreatedEvent.getOwnerUserId(),postCreatedEvent.getContent());
        Notification  notification = Notification.builder().message(message)
                                        .userId(postCreatedEvent.getUserId()).build();

        notificationService.addNotification(notification);

    }


    @KafkaListener(topics = "post_liked_topic")
    public void handlePostLiked(PostLikedEvent postLikedEvent){

        String message = String.format("User with id %d has liked your post with id %d", 
                postLikedEvent.getLikedByUserId(),postLikedEvent.getPostId());
        
        Notification notification = Notification.builder()
                                    .message(message)
                                    .userId(postLikedEvent.getPostOwnerId()).build();

        notificationService.addNotification(notification);

    }


}

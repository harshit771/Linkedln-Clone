package com.practice.linkedln.ConnectionsService.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.practice.linkedln.ConnectionsService.service.PersonService;
import com.practice.linkedln.userService.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceConsumer {

    private final PersonService personService;

    @KafkaListener(topics = "user_created_topic" )
    public void handleUserCreated(UserCreatedEvent userCreatedEvent){
        log.info("inside userservice consumer handleUserCreated method");
        personService.createPerson(userCreatedEvent.getUserId(), userCreatedEvent.getName());
        
    }


}

package com.practice.linkedln.ConnectionsService.service.impl;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.practice.linkedln.ConnectionsService.auth.AuthContextHolder;
import com.practice.linkedln.ConnectionsService.entity.Person;
import com.practice.linkedln.ConnectionsService.event.RequestAcceptEvent;
import com.practice.linkedln.ConnectionsService.event.RequestRejectEvent;
import com.practice.linkedln.ConnectionsService.event.RequestSendEvent;
import com.practice.linkedln.ConnectionsService.exception.BadRequestException;
import com.practice.linkedln.ConnectionsService.repository.PersonRepository;
import com.practice.linkedln.ConnectionsService.service.ConnectionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    private final PersonRepository personRepository;
    private final KafkaTemplate<Long,RequestSendEvent> requestSendKafkaTemplate;
    private final KafkaTemplate<Long,RequestAcceptEvent> requestAcceptKafkaTemplate;
    private final KafkaTemplate<Long,RequestRejectEvent> requestRejectKafkaTemplate;
    @Override
    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("Getting first degree connections for user id " + userId);
        return personRepository.getFirstDegreeConnections(userId);
    }

    @Override
    public void sendConnectionRequest(Long receiverId) {
        log.info("Sending request ");
        Long senderId = AuthContextHolder.getCurrentUserId();

        if (senderId.equals(receiverId)) {
            throw new BadRequestException("Both sender and receiver has same id");
        }

        boolean alreadyRequested = personRepository.connectionRequestExists(senderId, receiverId);

        if (alreadyRequested) {
            throw new BadRequestException("You have already requested to this person ");
        }

        boolean alreadyConnected = personRepository.alreadyConnected(senderId, receiverId);

        if (alreadyConnected) {
            throw new BadRequestException("You already connected with this person ");
        }

        personRepository.addConnectionRequest(senderId, receiverId);

        RequestSendEvent requestSendEvent = RequestSendEvent.builder().senderUserId(senderId)
                                                .receiverUserId(receiverId).build();

        log.info("Before sending notification");
        requestSendKafkaTemplate.send("request_send_topic",requestSendEvent);

        log.info("Request send successfully");

    }

    @Override
    public void acceptConnectionRequest(Long senderId) {
        log.info("Accepting request ");
        Long receiverId = AuthContextHolder.getCurrentUserId();

        if (senderId.equals(receiverId)) {
            throw new BadRequestException("Both sender and receiver has same id");
        }

        boolean alreadyConnected = personRepository.alreadyConnected(senderId, receiverId);

        if (alreadyConnected) {
            throw new BadRequestException("Already connected , can not accept request again");
        }

        boolean alreadyRequested = personRepository.connectionRequestExists(senderId, receiverId);

        if (!alreadyRequested) {
            throw new BadRequestException("No connection request exists ");
        }


        personRepository.acceptConnectionRequest(senderId, receiverId);

        RequestAcceptEvent requestAcceptEvent = RequestAcceptEvent.builder()
                                                .acceptUserId(receiverId)
                                                .requestedUserId(senderId).build();

        requestAcceptKafkaTemplate.send("request_accept_topic",requestAcceptEvent);
        log.info("request accepted successfully");

    }

    @Override
    public void rejectConnectionRequest(Long senderId) {
        log.info("Rejecting request ");
        Long receiverId = AuthContextHolder.getCurrentUserId();

        if (senderId.equals(receiverId)) {
            throw new BadRequestException("Both sender and receiver has same id");
        }
       
        boolean alreadyRequested = personRepository.connectionRequestExists(senderId, receiverId);
     
        if (!alreadyRequested) {
            throw new BadRequestException("No connection request exists ");
        }
        personRepository.rejectConnectionRequest(senderId, receiverId);

        RequestRejectEvent requestRejectEvent = RequestRejectEvent.builder()
                                                .receiverUserId(receiverId)
                                                .rejectUserId(senderId).build();

        requestRejectKafkaTemplate.send("request_reject_topic",requestRejectEvent);
        log.info("connection request rejected successfully");


    }

}

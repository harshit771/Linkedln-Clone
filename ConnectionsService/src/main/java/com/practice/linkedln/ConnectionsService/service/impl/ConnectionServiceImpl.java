package com.practice.linkedln.ConnectionsService.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.linkedln.ConnectionsService.auth.AuthContextHolder;
import com.practice.linkedln.ConnectionsService.entity.Person;
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

        log.info("connection request rejected successfully");


    }

}

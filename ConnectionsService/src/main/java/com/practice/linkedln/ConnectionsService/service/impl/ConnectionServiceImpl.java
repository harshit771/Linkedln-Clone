package com.practice.linkedln.ConnectionsService.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.practice.linkedln.ConnectionsService.entity.Person;
import com.practice.linkedln.ConnectionsService.repository.PersonRepository;
import com.practice.linkedln.ConnectionsService.service.ConnectionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService{

    private final PersonRepository personRepository;

    @Override
    public List<Person> getFirstDegreeConnections(Long userId) {
        log.info("Getting first degree connections for user id "+userId);
        return personRepository.getFirstDegreeConnections(userId);
    }

}

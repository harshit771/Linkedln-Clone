package com.practice.linkedln.ConnectionsService.service.impl;

import org.springframework.stereotype.Service;

import com.practice.linkedln.ConnectionsService.entity.Person;
import com.practice.linkedln.ConnectionsService.repository.PersonRepository;
import com.practice.linkedln.ConnectionsService.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public void createPerson(Long userId, String name) {
        Person person =  Person.builder().userId(userId).name(name).build();

        personRepository.save(person);
    }

}

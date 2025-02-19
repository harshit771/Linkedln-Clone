package com.practice.linkedln.ConnectionsService.service;

import java.util.List;

import com.practice.linkedln.ConnectionsService.entity.Person;

public interface ConnectionService {

    List<Person> getFirstDegreeConnections(Long userId);

}

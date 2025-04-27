package com.practice.linkedln.ConnectionsService.service;

import java.util.List;

import com.practice.linkedln.ConnectionsService.entity.Person;

public interface ConnectionService {

    List<Person> getFirstDegreeConnections(Long userId);

    public void sendConnectionRequest(Long receiverId);

    public void acceptConnectionRequest(Long senderId);

    public void rejectConnectionRequest(Long senderId);

}

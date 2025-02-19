package com.practice.linkedln.ConnectionsService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.linkedln.ConnectionsService.entity.Person;
import com.practice.linkedln.ConnectionsService.service.ConnectionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}/firstDegree")
    public ResponseEntity<List<Person>> getFistDegreeConnections(@PathVariable Long userId){
        List<Person> personList = connectionService.getFirstDegreeConnections(userId);
        return ResponseEntity.ok(personList);
    }


}

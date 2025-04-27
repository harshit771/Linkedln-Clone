package com.practice.linkedln.ConnectionsService.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.linkedln.ConnectionsService.entity.Person;
import com.practice.linkedln.ConnectionsService.service.ConnectionService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
@Slf4j  
public class ConnectionController {

    private final ConnectionService connectionService;

    @GetMapping("/{userId}/firstDegree")
    public ResponseEntity<List<Person>> getFistDegreeConnections(@PathVariable Long userId){
        List<Person> personList = connectionService.getFirstDegreeConnections(userId);
        return ResponseEntity.ok(personList);
    }

    @PostMapping("/request/{userId}")
    public ResponseEntity<Void> sendConnectionRequest(@PathVariable Long userId){
        
        connectionService.sendConnectionRequest(userId);

        return ResponseEntity.noContent().build();
        
    }

    @PostMapping("/accept/{userId}")
    public ResponseEntity<Void> acceptConnectionRequest(@PathVariable Long userId){
        
        connectionService.acceptConnectionRequest(userId);
        
        return ResponseEntity.noContent().build();
        
    }

    @PostMapping("/reject/{userId}")
    public ResponseEntity<Void> rejectConnectionRequest(@PathVariable Long userId){
        connectionService.rejectConnectionRequest(userId);
        
        return ResponseEntity.noContent().build();
        
    }


}

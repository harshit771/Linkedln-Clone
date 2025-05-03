package com.practice.linkedln.postService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.practice.linkedln.postService.dto.PersonDto;

@FeignClient(name="connection-service",path = "/connections" , url = "${CONNECTIONS_SERVICE_URI:}")
public interface ConnectionServiceClient {

    @GetMapping("/core/{userId}/firstDegree")
    List<PersonDto> getFistDegreeConnections(@PathVariable Long userId);

}

package com.practice.linkedln.ConnectionsService.event;

import lombok.Data;

@Data
public class RequestAcceptEvent {

    private Long requestedUserId;
    private Long acceptUserId;
}


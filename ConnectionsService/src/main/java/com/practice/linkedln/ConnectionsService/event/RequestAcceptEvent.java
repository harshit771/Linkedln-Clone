package com.practice.linkedln.ConnectionsService.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestAcceptEvent {

    private Long requestedUserId;
    private Long acceptUserId;
}


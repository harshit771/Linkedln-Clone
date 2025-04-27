package com.practice.linkedln.ConnectionsService.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestSendEvent {

    private Long senderUserId;
    private Long receiverUserId;
}

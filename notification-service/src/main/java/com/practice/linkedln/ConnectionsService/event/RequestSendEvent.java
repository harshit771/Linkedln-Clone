package com.practice.linkedln.ConnectionsService.event;


import lombok.Data;

@Data
public class RequestSendEvent {

    private Long senderUserId;
    private Long receiverUserId;
}

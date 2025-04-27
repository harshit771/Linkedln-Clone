package com.practice.linkedln.ConnectionsService.event;

import lombok.Data;

@Data
public class RequestRejectEvent {

    private Long rejectUserId;
    private Long receiverUserId;

}

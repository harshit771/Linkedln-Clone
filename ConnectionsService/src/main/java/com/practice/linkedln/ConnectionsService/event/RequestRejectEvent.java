package com.practice.linkedln.ConnectionsService.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestRejectEvent {

    private Long rejectUserId;
    private Long receiverUserId;

}

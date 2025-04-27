package com.practice.linkedln.userService.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreatedEvent {

    private Long userId;
    private String name;

}

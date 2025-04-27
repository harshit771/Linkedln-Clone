package com.practice.linkedln.userService.event;

import lombok.Data;

@Data
public class UserCreatedEvent {

    private Long userId;
    private String name;

}

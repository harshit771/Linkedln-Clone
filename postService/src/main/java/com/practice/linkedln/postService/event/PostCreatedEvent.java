package com.practice.linkedln.postService.event;

import lombok.*;

@Data
@Builder
public class PostCreatedEvent {
    
    private Long userId;
    private Long postId;
    private String content;

}

package com.practice.linkedln.postService.event;

import lombok.*;

@Data
public class PostLikedEvent {

    private Long postId;
    private Long postOwnerId;
    private Long likedByUserId;

}

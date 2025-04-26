package com.practice.linkedln.postService.event;

import lombok.*;

@Data
@Builder
public class PostLikedEvent {

    private Long postId;
    private Long likedByUserId;

}

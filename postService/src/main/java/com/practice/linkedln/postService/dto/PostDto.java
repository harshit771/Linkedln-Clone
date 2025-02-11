package com.practice.linkedln.postService.dto;

import java.time.LocalDateTime;
import lombok.Data;


@Data
public class PostDto {

    private String content;
   
    private Long userId;

    private LocalDateTime createdAt;

}

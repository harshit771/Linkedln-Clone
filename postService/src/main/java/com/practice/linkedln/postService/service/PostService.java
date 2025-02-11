package com.practice.linkedln.postService.service;

import com.practice.linkedln.postService.dto.PostCreatRequestDto;
import com.practice.linkedln.postService.dto.PostDto;

public interface PostService {

    PostDto creatPost(PostCreatRequestDto postCreatRequestDto, Long l);

}

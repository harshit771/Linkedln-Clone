package com.practice.linkedln.postService.service;

import java.util.List;

import com.practice.linkedln.postService.dto.PostCreateRequestDto;
import com.practice.linkedln.postService.dto.PostDto;

public interface PostService {

    PostDto creatPost(PostCreateRequestDto postCreatRequestDto, Long l);

    PostDto getPostById(Long postId);

    List<PostDto> getAllPostOfUser(Long userId);

}
